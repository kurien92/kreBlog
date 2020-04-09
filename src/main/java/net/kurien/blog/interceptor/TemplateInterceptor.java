package net.kurien.blog.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.common.template.metadata.TemplateCss;
import net.kurien.blog.common.template.metadata.TemplateJs;
import net.kurien.blog.common.template.metadata.TemplateMeta;
import net.kurien.blog.module.category.service.CategoryService;

public class TemplateInterceptor extends HandlerInterceptorAdapter {
	@Inject
	private Template template;
	
	@Inject
	CategoryService categoryService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getContextPath();
		
		template.setLang("ko");
		template.setCharset("utf-8");
		template.setTitle("Kurien's Blog");
		
		template.setMeta(new TemplateMeta());
		template.setCss(new TemplateCss());
		template.setHeadJs(new TemplateJs());
		template.setFootJs(new TemplateJs());
		
		template.setCategoryHTML(categoryService.getCategoryHTML(contextPath));
		
		return super.preHandle(request, response, handler);
	}
	
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	modelAndView.addObject("template", template);
    	
        super.postHandle(request, response, handler, modelAndView);
    }
}