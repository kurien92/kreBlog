package net.kurien.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kurien.blog.common.security.CurrentUser;
import net.kurien.blog.common.security.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.category.entity.Category;
import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.entity.ShortUrl;
import net.kurien.blog.module.shortUrl.service.ServiceShortUrlService;
import net.kurien.blog.module.shortUrl.service.ShortUrlService;
import net.kurien.blog.util.HtmlUtil;

@Controller
@RequestMapping("/post")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	private final Template template;
	private final PostService postService;
	private final CategoryService categoryService;
	private final ShortUrlService shortUrlService;
	private final ServiceShortUrlService serviceShortUrlService;

	@Autowired
	public PostController(Template template, PostService postService, CategoryService categoryService, ShortUrlService shortUrlService, ServiceShortUrlService serviceShortUrlService) {
		this.template = template;
		this.postService = postService;
		this.categoryService = categoryService;
		this.shortUrlService = shortUrlService;
		this.serviceShortUrlService = serviceShortUrlService;
	}

	/**
	 * 사용자가 목록 화면에 접속한다.
	 * 포스트 전체를 보여준다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(SearchCriteria criteria, HttpServletRequest request, HttpServletResponse response, Model model) {
		int totalRowCount = postService.getCount("N");
		PageMaker pageMaker = new PageMaker(criteria, totalRowCount);
		
		List<Post> posts = postService.getList("N", criteria);

		model.addAttribute("pageUrl", request.getContextPath() + "/post/list");
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("posts", posts);

		template.setTitle("Post list &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		
		return "post/list";
	}
	
	/**
	 * 사용자가 포스트 뷰 화면에 접속한다.
	 * 해당 번호의 포스트를 보여준다.
	 * 
	 * @param postNo
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/view/{postNo}", method = RequestMethod.GET)
	public String view(@PathVariable int postNo, Model model, @CurrentUser User user) throws Exception {
		Post post = postService.get(postNo, "N");
		Category category = categoryService.get(post.getCategoryId());

		ShortUrl shortUrl = null;

		ServiceShortUrl serviceShortUrl = serviceShortUrlService.get("post", postNo);
		if(serviceShortUrl != null) {
			shortUrl = shortUrlService.get(serviceShortUrl.getShortUrlNo());
		}
		
		model.addAttribute("post", post);
		model.addAttribute("category", category);
		model.addAttribute("shortUrl", shortUrl);
		model.addAttribute("user", user);

		template.setSubTitle(post.getPostSubject());
		template.setDescription(HtmlUtil.stripHtml(post.getPostContent()));
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/comment.css\">");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/plugin/highlight.css\">");
		
		return "post/view";
	}

	@RequestMapping(value = "/search/{postNo}")
	public String search(@PathVariable int postNo, Model model) {
		return "redirect: /post/view/" + postNo;
	}
}
