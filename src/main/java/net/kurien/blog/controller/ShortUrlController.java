package net.kurien.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.kurien.blog.module.shortUrl.service.ShortUrlService;

@Controller
@RequestMapping("/s")
public class ShortUrlController {
	private final ShortUrlService shortUrlService;

	@Autowired
	public ShortUrlController(ShortUrlService shortUrlService) {
		this.shortUrlService = shortUrlService;
	}

	@RequestMapping("/{encodedUrl}")
	@ResponseStatus(value=HttpStatus.TEMPORARY_REDIRECT)
	public String shortUrl(@PathVariable String encodedUrl, Model model) throws Exception {
		String redirectUrl = shortUrlService.getRedirectUrl(encodedUrl);
		
		model.addAttribute("redirectUrl", redirectUrl);
		
		return "shortUrl/redirect";
	}
}
