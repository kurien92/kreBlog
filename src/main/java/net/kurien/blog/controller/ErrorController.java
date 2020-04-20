package net.kurien.blog.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.exception.handler.BasicExceptionHandler;

@Controller
@RequestMapping("/error")
public class ErrorController {
	Logger logger = LoggerFactory.getLogger(BasicExceptionHandler.class);

	@Inject
	private Template template;

	@RequestMapping
	public String defaultError() {
		return "error/exception";
	}

	@RequestMapping("/accessError")
	public String accessDenied(HttpServletRequest request, HttpServletResponse response, Model model) {
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		String referer = request.getHeader("referer");

		model.addAttribute("referer", referer);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/error.css\">");
		
		logger.info("exception requestURI: " + requestURI);

		model.addAttribute("exceptionMsg", "접근 권한이 없습니다.");
		model.addAttribute("exceptionDescription", "접근권한이 없습니다.<br>접근권한이 있는 계정으로 로그인하시기 바랍니다.");

		return "error/exception";
	}

	@RequestMapping("/notFound")
	public String notFound(HttpServletRequest request, HttpServletResponse response, Model model) {
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		String referer = request.getHeader("referer");

		model.addAttribute("referer", referer);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/error.css\">");
		
		logger.info("exception requestURI: " + requestURI);

		model.addAttribute("exceptionMsg", "페이지를 찾을 수 없습니다.");
		model.addAttribute("exceptionDescription", "페이지가 이동되었거나 삭제되었습니다.<br>카테고리를 선택하시거나 아래에 표시된 버튼을 통해 이동하시기 바랍니다.");

		return "error/exception";
	}

	@RequestMapping("/internalServerError")
	public String internalServerError(HttpServletRequest request, HttpServletResponse response, Model model) {
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		String referer = request.getHeader("referer");

		model.addAttribute("referer", referer);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/error.css\">");
		
		logger.info("exception requestURI: " + requestURI);

		model.addAttribute("exceptionMsg", "예상하지 못한 오류가 발생했습니다.");
		model.addAttribute("exceptionDescription", "예상하지 못한 오류가 발생했습니다.<br>카테고리를 선택하시거나 아래에 표시된 버튼을 통해 이동하시기 바랍니다.");

		return "error/exception";
	}
	
}
