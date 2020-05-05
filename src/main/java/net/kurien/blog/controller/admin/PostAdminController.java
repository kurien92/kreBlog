package net.kurien.blog.controller.admin;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.module.category.vo.Category;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.post.vo.Post;
import net.kurien.blog.module.post.vo.PostPublishStatus;
import net.kurien.blog.module.post.vo.PostViewStatus;
import net.kurien.blog.module.shortUrl.service.ServiceShortUrlService;
import net.kurien.blog.module.shortUrl.service.ShortUrlService;
import net.kurien.blog.module.shortUrl.vo.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.vo.ShortUrl;
import net.kurien.blog.util.HtmlUtil;
import net.kurien.blog.util.RequestUtil;

@Controller
@RequestMapping("/admin/post")
public class PostAdminController {
	private static final Logger logger = LoggerFactory.getLogger(PostAdminController.class);

	@Inject
	private PostService postService;
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private ShortUrlService shortUrlService;
	
	@Inject
	private ServiceShortUrlService serviceShortUrlService;
	
	@Inject
	private Template template;
	
	/**
	 * 관리자가 목록 화면에 접속한다.
	 * 포스트 전체를 보여준다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Post> posts = postService.getList("Y");
		model.addAttribute("posts", posts);

		template.setTitle("Post List &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		
		return "admin/post/admin/list";
	}
	
	/**
	 * 관리자가 포스트를 쓰는 화면을 출력한다.
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Model model) throws Exception {
		List<Category> categories = categoryService.getList();
		
		model.addAttribute("categories", categories);
		model.addAttribute("formAction", "writeUpdate");
		model.addAttribute("formSubmit", "작성");
		
		template.setTitle("Post Write &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		
		return "admin/post/admin/write";
	}
	
	/**
	 * 관리자가 작성한 포스트를 저장한다.
	 * 
	 * @param post
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/writeUpdate", method = RequestMethod.POST)
	public String writeUpdate(HttpServletRequest request, Post post, Integer[] fileNos) throws Exception {
		post.setPostAuthor("Kurien");
		post.setPostWriteTime(Calendar.getInstance().getTime());
		post.setPostWriteIp(RequestUtil.getRemoteAddr(request));

		postService.write(post, fileNos);
		
		try {
			createShortUrl(request, post);
		} catch(Exception e) {
			logger.warn("shortUrl 생성을 실패했습니다.");
		}
		
		return "redirect:/admin/post/list";
	}
	
	private void createShortUrl(HttpServletRequest request, Post post) {
		// TODO Auto-generated method stub
		String url = "https://" + request.getServerName() + request.getContextPath() + "/post/view/" + post.getPostNo();
		
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setRealUrl(url);
		shortUrl.setCreateIp(RequestUtil.getRemoteAddr(request));
		
		shortUrlService.set(shortUrl);
		
		ServiceShortUrl serviceShortUrl = new ServiceShortUrl();
		serviceShortUrl.setServiceName("post");
		serviceShortUrl.setServiceNo(post.getPostNo());
		serviceShortUrl.setShortUrlNo(shortUrl.getShortUrlNo());
		serviceShortUrl.setCreateIp(RequestUtil.getRemoteAddr(request));
		
		serviceShortUrlService.add(serviceShortUrl);
	}

	/**
	 * 관리자가 작성했던 포스트를 수정한다.
	 * 
	 * @param postNo
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/modify/{postNo}", method = RequestMethod.GET)
	public String modify(@PathVariable int postNo, Model model) throws Exception {
		Post post = postService.get(postNo, "Y");
		List<Category> categories = categoryService.getList();

		String htmlEscapeContent = HtmlUtil.escapeHtml(post.getPostContent());
		post.setPostContent(htmlEscapeContent);
		
		ServiceShortUrl serviceShortUrl = serviceShortUrlService.get("post", postNo);
		ShortUrl shortUrl = null;
		
		if(serviceShortUrl != null) {
			shortUrl = shortUrlService.get(serviceShortUrl.getShortUrlNo());
		}
		
		model.addAttribute("post", post);
		model.addAttribute("categories", categories);
		model.addAttribute("shortUrl", shortUrl);
		model.addAttribute("formAction", "modifyUpdate");
		model.addAttribute("formSubmit", "수정");
		
		template.setTitle(post.getPostSubject() + " : Post Modify &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		
		return "admin/post/admin/write";
	}

	/**
	 * 관리자가 수정한 포스트를 저장한다.
	 * 
	 * @param post
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/modifyUpdate", method = RequestMethod.POST)
	public String modify(Post post, Integer[] fileNos, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(post.getPostView() == null) {
			post.setPostView(PostViewStatus.FALSE);
		}
		
		if(post.getPostPublish() == null) {
			post.setPostPublish(PostPublishStatus.FALSE);
		}
		
		if(post.getPostWriteIp() == null) {
			post.setPostWriteIp(RequestUtil.getRemoteAddr(request));
		}
		
		postService.modify(post, fileNos);
		
		return "redirect:/admin/post/list";
	}
	
	/**
	 * 관리자가 작성했던 포스트를 삭제한다.
	 * 
	 * @param postNo
	 * @return
	 */
	@RequestMapping(value = "/delete/{postNo}", method = RequestMethod.GET)
	public String delete(@PathVariable int postNo) {
		postService.delete(postNo);
		
		return "redirect:/admin/post/list";
	}
	
	@RequestMapping(value = "/preview/{postNo}", method = RequestMethod.GET)
	public String preview(@PathVariable int postNo, Model model) throws Exception {
		Post post = postService.get(postNo, "Y");
		Category category = categoryService.get(post.getCategoryId());

		ShortUrl shortUrl = null;

		ServiceShortUrl serviceShortUrl = serviceShortUrlService.get("post", postNo);
		if(serviceShortUrl != null) {
			shortUrl = shortUrlService.get(serviceShortUrl.getShortUrlNo());
		}

		model.addAttribute("post", post);
		model.addAttribute("category", category);
		model.addAttribute("shortUrl", shortUrl);

		template.setSubTitle(post.getPostSubject());
		template.setDescription(HtmlUtil.stripHtml(post.getPostContent()));
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		
		return "post/view";
	}
}
