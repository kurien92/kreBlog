package net.kurien.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	@RequestMapping("/error/accessError")
	public void accessDenied(Authentication auth, Model model) {
		model.addAttribute("msg", "접근 권한이 없습니다.");
	}
}
