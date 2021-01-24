package net.kurien.blog.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/admin/category")
public class CategoryAdminController {
	private static final Logger logger = LoggerFactory.getLogger(CategoryAdminController.class);

	private final Template template;
	private final CategoryService categoryService;

	@Autowired
	public CategoryAdminController(Template template, CategoryService categoryService) {
		this.template = template;
		this.categoryService = categoryService;
	}

	/**
	 * 관리자가 목록 화면에 접속한다.
	 * 포스트 전체를 보여준다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Category> categories = categoryService.getList();
		model.addAttribute("categories", categories);
		
		template.setTitle("Category List &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");
		
		return "admin/category/admin/list";
	}
	
	/**
	 * 관리자가 포스트를 쓰는 화면을 출력한다.
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String write(Model model) throws Exception {
		List<Category> categories = categoryService.getList();
		
		model.addAttribute("parentCategories", categories);
		model.addAttribute("formAction", "addUpdate");
		model.addAttribute("formSubmit", "추가");
		
		return "admin/category/admin/add";
	}
	
	/**
	 * 관리자가 작성한 포스트를 저장한다.
	 * 
	 * @param post
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addUpdate", method = RequestMethod.POST)
	public String writeUpdate(HttpServletRequest request, Category category) throws Exception {
		categoryService.create(category);

		return "redirect:/admin/category/list";
	}
	
	/**
	 * 관리자가 작성했던 포스트를 수정한다.
	 * 
	 * @param postNo
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/modify/{categoryId}", method = RequestMethod.GET)
	public String modify(@PathVariable String categoryId, Model model) throws Exception {
		Category category = categoryService.get(categoryId);
		List<Category> categories = categoryService.getList();

		model.addAttribute("category", category);
		model.addAttribute("parentCategories", categories);
		model.addAttribute("formAction", "modifyUpdate");
		model.addAttribute("formSubmit", "수정");
		
		return "admin/category/admin/add";
	}

	/**
	 * 관리자가 수정한 포스트를 저장한다.
	 * 
	 * @param post
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/modifyUpdate", method = RequestMethod.POST)
	public String modify(Category category) throws Exception {
		categoryService.modify(category);
		
		return "redirect:/admin/category/list";
	}
	
	/**
	 * 관리자가 작성했던 포스트를 삭제한다.
	 * 
	 * @param postNo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/remove/{categoryId}", method = RequestMethod.GET)
	public String delete(@PathVariable String categoryId) throws Exception {
		categoryService.remove(categoryId);
		
		return "redirect:/admin/category/list";
	}
}
