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

public class TemplateInterceptor extends HandlerInterceptorAdapter {
	@Inject
	private Template template;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		template.setLang("ko");
		template.setCharset("utf-8");
		template.setTitle("Kurien's Blog");
		
		template.setMeta(new TemplateMeta());
		template.setCss(new TemplateCss());
		template.setHeadJs(new TemplateJs());
		template.setFootJs(new TemplateJs());
		
		return super.preHandle(request, response, handler);
	}
	
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	modelAndView.addObject("template", template);
    	
        super.postHandle(request, response, handler, modelAndView);
    }
}