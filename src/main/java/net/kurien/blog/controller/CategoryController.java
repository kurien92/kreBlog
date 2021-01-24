package net.kurien.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.category.entity.Category;
import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.service.PostService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	private final Template template;
	private final PostService postService;
	private final CategoryService categoryService;

	@Autowired
	public CategoryController(Template template, PostService postService, CategoryService categoryService) {
		this.template = template;
		this.postService = postService;
		this.categoryService = categoryService;
	}
	
	@RequestMapping("/{categoryId}")
	public String list(@PathVariable String categoryId, SearchCriteria criteria, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Category category = categoryService.get(categoryId);
		
		if(category == null) {
			throw new NotFoundDataException("존재하지 않는 카테고리입니다.");
		}
		
		int totalRowCount = postService.getCountByCategoryId(category.getCategoryId(), "N");
		PageMaker pageMaker = new PageMaker(criteria, totalRowCount);
		
		List<String> categories = new ArrayList<>();
		categories.add(category.getCategoryId());
		
		List<Post> posts = postService.getListByCategoryIds(categories, "N", criteria);

		model.addAttribute("pageUrl", request.getContextPath() + "/category/" + categoryId);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("posts", posts);
		
		template.setSubTitle("Category " + category.getCategoryName());
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");

		return "post/list";
	}
}