package net.kurien.blog.controller.admin;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.kurien.blog.controller.PostController;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.post.vo.Post;

@Controller
@RequestMapping("/admin/post")
public class PostAdminController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Inject
	private PostService postService;
	
	/**
	 * 관리자가 목록 화면에 접속한다.
	 * 포스트 전체를 보여준다.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Post> posts = postService.getList();
		model.addAttribute("posts", posts);
		
		return "post/admin/list";
	}
	
	/**
	 * 관리자가 포스트를 쓰는 화면을 출력한다.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "post/admin/write";
	}
	
	/**
	 * 관리자가 작성한 포스트를 저장한다.
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeUpdate(Post post) {
//		postService.write(post);

		return "redirect:/admin/post/modify";
	}
	
	/**
	 * 관리자가 작성했던 포스트를 수정한다.
	 * 
	 * @param postNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("no") int postNo, Model model) {
		Post post = postService.get(postNo);
		model.addAttribute("post", post);
		
		return "post/admin/write";
	}

	/**
	 * 관리자가 수정한 포스트를 저장한다.
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(Post post) {
//		postService.modify(post);
		
		return "redirect:/admin/post/modify";
	}
	
	/**
	 * 관리자가 작성했던 포스트를 삭제한다.
	 * 
	 * @param postNo
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("no") int postNo) {
		postService.delete(postNo);
		
		return "redirect:/admin/post/list";
	}
}
