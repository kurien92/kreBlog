package net.kurien.blog.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kurien.blog.module.comment.entity.Comment;
import net.kurien.blog.module.comment.service.CommentService;

@Controller 
@RequestMapping("/admin/comment")
public class CommentAdminController {
	private final CommentService commentService;

	@Autowired
	public CommentAdminController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Comment> comments = commentService.getList();
		
		model.addAttribute("comments", comments);
		
		return "admin/comment/admin/list";
	}
	
	@RequestMapping(value = "/delete/{commentNo}", method = RequestMethod.GET)
	public String delete(@PathVariable int commentNo, Model model) {
		commentService.remove(commentNo);

		return "redirect:/admin/comment/list";
	}
}
