package net.kurien.blog.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kurien.blog.common.aop.security.ReloadableFilterInvocationSecurityMetadataSource;
import net.kurien.blog.common.template.Template;
import net.kurien.blog.common.template.metadata.TemplateCss;
import net.kurien.blog.common.template.metadata.TemplateJs;
import net.kurien.blog.common.template.metadata.TemplateMeta;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private Template template;
	
//	@Inject
//	private ReloadableFilterInvocationSecurityMetadataSource reloadableFilterInvocationSecurityMetadataSource;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		TemplateMeta tMeta = new TemplateMeta();
		TemplateCss tCss = new TemplateCss();
		tCss.add("<link rel=\"stylesheet\" href=\"/css/home.css\">");
		TemplateJs tHJs = new TemplateJs();
		TemplateJs tFJs = new TemplateJs();
		
		Template template = new Template();
		template.setLang("ko");
		template.setCharset("utf-8");
		template.setTitle("Kurien's Blog");
		template.setMeta(tMeta);
		template.setCss(tCss);
		template.setHeadJs(tHJs);
		template.setFootJs(tFJs);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/home.css\">");
		
		// model.addAttribute("bcrypt", passwordEncoder.encode("password"));
		
//		reloadableFilterInvocationSecurityMetadataSource.reload();
		
		return "home";
	}

	@RequestMapping(value="/auth/signin", method={RequestMethod.GET, RequestMethod.POST})
	public String authLogin(HttpServletRequest request, Model model) {
		TemplateMeta tMeta = new TemplateMeta();
		TemplateCss tCss = new TemplateCss();
		tCss.add("<link rel=\"stylesheet\" href=\"/css/module/auth.css\">");
		TemplateJs tHJs = new TemplateJs();
		TemplateJs tFJs = new TemplateJs();
		
		Template template = new Template();
		template.setLang("ko");
		template.setCharset("utf-8");
		template.setTitle("Sign in &dash; Kurien's Blog");
		template.setMeta(tMeta);
		template.setCss(tCss);
		template.setHeadJs(tHJs);
		template.setFootJs(tFJs);
		
		model.addAttribute("templateConfig", template);
		
		return "auth/signin";
	}
	
	@RequestMapping(value="/admin/test", method=RequestMethod.GET)
	public String adminTest() {
		return "home";
	}
}
