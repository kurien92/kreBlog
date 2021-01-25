package net.kurien.blog.exception.handler;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.common.template.metadata.TemplateCss;
import net.kurien.blog.common.template.metadata.TemplateJs;
import net.kurien.blog.common.template.metadata.TemplateMeta;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.category.service.CategoryService;

import java.io.FileNotFoundException;

@ControllerAdvice
public class BasicExceptionHandler {
	Logger logger = LoggerFactory.getLogger(BasicExceptionHandler.class);

	private final Template template;
	private final CategoryService categoryService;

	@Autowired
	public BasicExceptionHandler(Template template, CategoryService categoryService) {
		this.template = template;
		this.categoryService = categoryService;
	}

	@ExceptionHandler({NotFoundDataException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String notFoundExceptionHandler(HttpServletRequest request, HttpServletResponse response, Model model, Exception nfe) {
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		String referer = request.getHeader("referer");

		model.addAttribute("referer", referer);

		try {
			logger.info("exception requestURI: " + requestURI, nfe);

			model.addAttribute("template", this.setTemplate(request));
			model.addAttribute("exceptionMsg", nfe.getMessage());
			model.addAttribute("exceptionDescription", "페이지가 이동되었거나 삭제되었습니다.<br>카테고리를 선택하시거나 아래에 표시된 버튼을 통해 이동하시기 바랍니다.");

			return "error/exception";
		} catch(Exception e) {
			logger.error("exception requestURI: " + requestURI, e);
			return "error/fatalError";
		}
	}

	@ExceptionHandler({FileNotFoundException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String fileNotFoundExceptionHandler(HttpServletRequest request, HttpServletResponse response, Model model, Exception fnfe) {
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		String referer = request.getHeader("referer");

		model.addAttribute("referer", referer);

		try {
			logger.info("exception requestURI: " + requestURI, fnfe);

			model.addAttribute("template", this.setTemplate(request));
			model.addAttribute("exceptionMsg", fnfe.getMessage());
			model.addAttribute("exceptionDescription", "파일이 존재하지 않습니다.<br>카테고리를 선택하시거나 아래에 표시된 버튼을 통해 이동하시기 바랍니다.");

			return "error/exception";
		} catch(Exception e) {
			logger.error("exception requestURI: " + requestURI, e);
			return "error/fatalError";
		}
	}
	
	@ExceptionHandler({InvalidRequestException.class})
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String invalidRequestExceptionhandler(HttpServletRequest request, HttpServletResponse response, Model model, Exception ire) {
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		String referer = request.getHeader("referer");

		model.addAttribute("referer", referer);
		
		try {
			logger.error("exception requestURI: " + requestURI, ire);
	
	    	model.addAttribute("template", this.setTemplate(request));
			model.addAttribute("exceptionMsg", ire.getMessage());
			model.addAttribute("exceptionDescription", "잘못된 경로로 접근하셨습니다.<br>카테고리를 선택하시거나 아래에 표시된 버튼을 통해 정상적인 경로로 접근하여주시기 바랍니다.");

			return "error/exception";
		} catch(Exception e) {
			logger.error("exception requestURI: " + requestURI, e);
			return "error/fatalError";
		}
	}
	
	@ExceptionHandler({Exception.class})
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Model model, Exception e) {
			String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
			String referer = request.getHeader("referer");

			model.addAttribute("referer", referer);

		try {
			logger.warn("exception requestURI: " + requestURI, e);
	
	    	model.addAttribute("template", this.setTemplate(request));
			model.addAttribute("exceptionMsg", "알 수 없는 오류가 발생했습니다.");
			model.addAttribute("exceptionDescription", "예상하지 못한 오류가 발생했습니다.<br>카테고리를 선택하시거나 아래에 표시된 버튼을 통해 이동하시기 바랍니다.");

			return "error/exception";
		} catch(Exception fatalError) {
			logger.error("exception requestURI: " + requestURI, fatalError);
			return "error/fatalError";
		}
	}
	
	private Template setTemplate(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		
		template.setLang("ko");
		template.setCharset("utf-8");
		template.setMainTitle("Kurien's Blog");
		template.setSubTitle("오류 페이지");
		template.setDescription("Kurien's Blog는 프로그래밍과 개발 전반에 대한 내용을 다루는 블로그입니다.");
		
		template.setMeta(new TemplateMeta());
		template.setCss(new TemplateCss());
		template.setHeadJs(new TemplateJs());
		template.setFootJs(new TemplateJs());
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/error.css\">");
		
		template.setCategoryHTML(categoryService.getCategoryHTML(contextPath));
    	
    	// subTitle이 있다면 subTitle을 포함하여 표시한다. 
    	String title = template.getMainTitle(); 
    	
    	if(template.getSubTitle().equals("") == false) {
    		title = template.getSubTitle() + " | " + title;
    	}
    	
    	template.setTitle(title);
    	
    	return template;
	}
}
