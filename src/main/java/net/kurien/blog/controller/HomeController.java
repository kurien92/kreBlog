package net.kurien.blog.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.domain.Criteria;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.service.PostService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	private Template template;
	
	@Inject
	private PostService postService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {
		Criteria criteria = new SearchCriteria(1, 10);
		int totalRowCount = postService.getCount("N", criteria);
		PageMaker pageMaker = new PageMaker(criteria, totalRowCount);
		
		List<Post> posts = postService.getList("N", criteria);
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("posts", posts);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/home.css\">");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");

		return "post/list";
	}
	
	@RequestMapping(value = "/rss", method = RequestMethod.GET, produces="application/xml;charset=utf-8")
	public @ResponseBody String rss(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Criteria criteria = new Criteria(1, 500);
		
		List<Post> posts = postService.getList("N", criteria);

		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		
		Element root = new Element("rss");
		root.setAttribute(new Attribute("version", "2.0"));
		
		Element channel = new Element("channel");
		root.addContent(channel);
		
		channel.addContent(new Element("title").setText("<![CDATA[Kurien's Blog]]>"));
		channel.addContent(new Element("link").setText("https://kurien.net/"));
		channel.addContent(new Element("description").setText("<![CDATA[Kurien's Blog는 프로그래밍과 개발 전반에 대한 내용을 다루는 블로그입니다.]]>"));

		for(int i = 0; i < posts.size(); i++) {
			Element item = new Element("item");

			String link = "https://www.kurien.net/post/view/" + posts.get(i).getPostNo();
			
			item.addContent(new Element("title").setText("<![CDATA[" + posts.get(i).getPostSubject() + "]]>"));
			item.addContent(new Element("link").setText(link));
			item.addContent(new Element("description").setText("<![CDATA[" + posts.get(i).getPostContent() + "]]>"));
			item.addContent(new Element("pubDate").setText(sdf.format(posts.get(i).getPostWriteTime())));
			item.addContent(new Element("guid").setText(link));
			
			channel.addContent(item);
		}
		
		Document doc = new Document();
		doc.setRootElement(root);
		
		Format f = Format.getPrettyFormat();
		f.setEncoding("UTF-8");
		f.setLineSeparator("\r\n");
		
		
		XMLOutputter outputter = new XMLOutputter(f);
		
		return outputter.outputString(doc);
	}
}
