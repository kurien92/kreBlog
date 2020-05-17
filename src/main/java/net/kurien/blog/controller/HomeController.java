package net.kurien.blog.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.domain.Criteria;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.service.PostService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	private Template template;
	
	@Inject
	private PostService postService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {
		Criteria criteria = new SearchCriteria(1, 10);
		int totalRowCount = postService.getCount("N", criteria);
		PageMaker pageMaker = new PageMaker(criteria, totalRowCount);
		
		List<Post> posts = postService.getList("N", criteria);
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("posts", posts);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/home.css\">");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");

		return "post/list";
	}

	@RequestMapping(value="/auth/signin", method={RequestMethod.GET, RequestMethod.POST})
	public String authLogin(HttpServletRequest request, Model model) {
		template.setTitle("Sign in &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/auth.css\">");
		
		return "auth/signin";
	}
}
