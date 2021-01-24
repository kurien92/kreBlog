package net.kurien.blog.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.NotFoundDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.module.category.entity.Category;
import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.module.file.entity.File;
import net.kurien.blog.module.file.entity.ServiceFile;
import net.kurien.blog.module.file.service.FileService;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.entity.PostPublishStatus;
import net.kurien.blog.module.post.entity.PostViewStatus;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.entity.ShortUrl;
import net.kurien.blog.module.shortUrl.service.ServiceShortUrlService;
import net.kurien.blog.module.shortUrl.service.ShortUrlService;
import net.kurien.blog.util.HtmlUtil;
import net.kurien.blog.util.RequestUtil;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/post")
public class PostAdminController {
	private static final Logger logger = LoggerFactory.getLogger(PostAdminController.class);

	private final Template template;
	private final PostService postService;
	private final CategoryService categoryService;
	private final ShortUrlService shortUrlService;
	private final ServiceShortUrlService serviceShortUrlService;
	private final FileService fileService;
	private final ServiceFileService serviceFileService;

	@Autowired
	public PostAdminController(Template template, PostService postService, CategoryService categoryService, ShortUrlService shortUrlService, ServiceShortUrlService serviceShortUrlService, FileService fileService, ServiceFileService serviceFileService) {
		this.template = template;
		this.postService = postService;
		this.categoryService = categoryService;
		this.shortUrlService = shortUrlService;
		this.serviceShortUrlService = serviceShortUrlService;
		this.fileService = fileService;
		this.serviceFileService = serviceFileService;
	}

	/**
	 * 관리자가 목록 화면에 접속한다.
	 * 포스트 전체를 보여준다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(SearchCriteria criteria, Model model, HttpServletRequest request, HttpServletResponse response) {
		int totalRowCount = postService.getCount("Y");
		PageMaker pageMaker = new PageMaker(criteria, totalRowCount);

		List<Post> posts = postService.getList("Y", criteria);

		model.addAttribute("pageUrl", request.getContextPath() + "/admin/post/list");
		model.addAttribute("pageMaker", pageMaker);
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
		post.setPostWriteIp(RequestUtil.getRemoteAddr(request));

		postService.write(post, fileNos);
		
		try {
			createShortUrl(request, post);
		} catch(Exception e) {
			logger.warn("shortUrl 생성을 실패했습니다.");
		}
		
		return "redirect:/admin/post/list";
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
		ShortUrl shortUrl = null;
		List<File> files = null;
		
		Post post = postService.get(postNo, "Y");
		List<Category> categories = categoryService.getList();

		String htmlEscapeContent = HtmlUtil.escapeHtml(post.getPostContent());
		post.setPostContent(htmlEscapeContent);
		
		ServiceShortUrl serviceShortUrl = serviceShortUrlService.get("post", postNo);
		
		if(serviceShortUrl != null) {
			shortUrl = shortUrlService.get(serviceShortUrl.getShortUrlNo());
		}

		List<ServiceFile> serviceFiles = serviceFileService.getFiles("post", postNo);

		if(serviceFiles.size() > 0) {
			List<Integer> fileNos = new ArrayList<>();
			
			for(ServiceFile serviceFile : serviceFiles) {
				fileNos.add(serviceFile.getFileNo());
			}
			
			files = fileService.getList(fileNos);
		}

		model.addAttribute("post", post);
		model.addAttribute("categories", categories);
		model.addAttribute("shortUrl", shortUrl);
		model.addAttribute("files", files);
		
		model.addAttribute("formAction", "modifyUpdate");
		model.addAttribute("formSubmit", "수정");
		
		template.setTitle(post.getPostSubject() + " : Post Modify &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/plugin/highlight.css\">");
		
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
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/comment.css\">");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/plugin/highlight.css\">");
		
		return "post/view";
	}

	@RequestMapping(value = "/deleteFile/{fileNo}")
	public @ResponseBody JsonObject deleteFile(@PathVariable int fileNo) {
		JsonObject json = new JsonObject();

		try {
			serviceFileService.remove(fileNo);
		} catch(NotFoundDataException e) {
			e.printStackTrace();
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", e.getMessage());

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", "알 수 없는 오류가 발생했습니다.");

			return json;
		}

		json.addProperty("result", "success");
		json.add("value", new JsonObject());
		json.addProperty("message", "");

		return json;
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
}
