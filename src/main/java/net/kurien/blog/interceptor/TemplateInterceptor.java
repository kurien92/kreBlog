package net.kurien.blog.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.common.template.metadata.TemplateCss;
import net.kurien.blog.common.template.metadata.TemplateJs;
import net.kurien.blog.common.template.metadata.TemplateMeta;
import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.util.HtmlUtil;

public class TemplateInterceptor extends HandlerInterceptorAdapter {
	@Inject
	private Template template;
	
	@Inject
	CategoryService categoryService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getContextPath();
		
		template.setLang("ko");
		template.setCharset("utf-8");
		template.setMainTitle("Kurien's Blog");
		template.setSubTitle("");
		template.setDescription("Kurien's Blog는 프로그래밍과 개발 전반에 대한 내용을 다루는 블로그입니다.");
		
		template.setMeta(new TemplateMeta());
		template.setCss(new TemplateCss());
		template.setHeadJs(new TemplateJs());
		template.setFootJs(new TemplateJs());
		
		template.setCategoryHTML(categoryService.getCategoryHTML(contextPath));
		
		return super.preHandle(request, response, handler);
	}
	
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	// 사이트 설명이 있는 경우 150자로 잘라 처리함
    	String description = template.getDescription();
    	description = HtmlUtil.escapeHtml(description);
    	
    	if(description.length() > 150) {
    		description = description.substring(0, 150);
    		template.setDescription(description + "...");
    	}
    	
    	
    	// subTitle이 있다면 subTitle을 표시한다.
    	String title = null;
    	
    	if(template.getSubTitle().equals("") == false) {
    		title = template.getSubTitle();
    	} else {
			title = template.getMainTitle();
		}
    	
    	template.setTitle(title);
    	
    	modelAndView.addObject("template", template);
    	
        super.postHandle(request, response, handler, modelAndView);
    }
}