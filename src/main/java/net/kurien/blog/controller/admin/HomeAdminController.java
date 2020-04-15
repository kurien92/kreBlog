package net.kurien.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class HomeAdminController {

	@RequestMapping(value="", method = RequestMethod.GET)
	public String main() {
		return "admin";
	}
}
