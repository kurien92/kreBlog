package net.kurien.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kurien.blog.common.template.Template;

@Controller
public class AuthController {
	private final Template template;

	@Autowired
	public AuthController(Template template) {
		this.template = template;
	}
	
	@RequestMapping(value="/auth/signin", method={RequestMethod.GET, RequestMethod.POST})
	public String authLogin(HttpServletRequest request, Model model) {
		template.setTitle("Sign in &dash; Kurien's Blog");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/auth.css\">");
		
		return "auth/signin";
	}
}
