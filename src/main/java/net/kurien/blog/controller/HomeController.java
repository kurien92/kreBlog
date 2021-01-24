package net.kurien.blog.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.module.content.service.ContentService;
import net.kurien.blog.module.sitemap.SitemapCreatable;
import net.kurien.blog.module.sitemap.SitemapDto;
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.domain.PageMaker;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.service.PostService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private final Template template;
	private final ContentService contentService;
	private final PostService postService;
	private final CategoryService categoryService;

	@Autowired
	public HomeController(Template template, ContentService contentService, PostService postService, CategoryService categoryService) {
		this.template = template;
		this.contentService = contentService;
		this.postService = postService;
		this.categoryService = categoryService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(SearchCriteria criteria, HttpServletRequest request, Locale locale, Model model) {
		int totalRowCount = postService.getCount("N");
		PageMaker pageMaker = new PageMaker(criteria, totalRowCount);
		
		List<Post> posts = postService.getList("N", criteria);

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("pageUrl", request.getContextPath() + "/");
		model.addAttribute("posts", posts);
		
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/home.css\">");
		template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/post.css\">");

		return "post/list";
	}
	
	@RequestMapping(value = "/rss", method = RequestMethod.GET, produces="application/xml;charset=utf-8")
	public @ResponseBody String rss(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		SearchCriteria criteria = new SearchCriteria(1, 500);
		
		List<Post> posts = postService.getList("N", criteria);

		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		
		Element root = new Element("rss");
		root.setAttribute(new Attribute("version", "2.0"));
		
		Element channel = new Element("channel");
		root.addContent(channel);
		
		channel.addContent(new Element("title").addContent(new CDATA("Kurien's Blog")));
		channel.addContent(new Element("link").setText("https://www.kurien.net/"));
		channel.addContent(new Element("description").addContent(new CDATA("Kurien's Blog는 프로그래밍과 개발 전반에 대한 내용을 다루는 블로그입니다.")));

		for(int i = 0; i < posts.size(); i++) {
			Element item = new Element("item");

			String link = "https://www.kurien.net/post/view/" + posts.get(i).getPostNo();
			
			item.addContent(new Element("title").addContent(new CDATA(posts.get(i).getPostSubject())));
			item.addContent(new Element("link").setText(link));
			item.addContent(new Element("description").addContent(new CDATA(posts.get(i).getPostContent())));
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

	/**
	 * 사이트맵에 추가될 컨텐츠의 링크 작성에 필요한 정보를 가져온다.
	 *
	 *
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sitemap", method = RequestMethod.GET, produces="application/xml;charset=utf-8")
	public @ResponseBody String sitemap(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SitemapCreatable> sitemapCreatables = new ArrayList<>();
		List<SitemapDto> sitemapDtos = new ArrayList<>();

		String siteUrl = "https://www.kurien.net";

		sitemapCreatables.add((SitemapCreatable) contentService);
		sitemapCreatables.add((SitemapCreatable) postService);
		sitemapCreatables.add((SitemapCreatable) categoryService);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH);

		Namespace nsSitemap = Namespace.getNamespace("http://www.sitemaps.org/schemas/sitemap/0.9");

		Element root = new Element("urlset", nsSitemap);

		for(SitemapCreatable sitemapCreatable : sitemapCreatables) {
			sitemapDtos.addAll(sitemapCreatable.sitemap(siteUrl));
		}

		for(SitemapDto sitemapDto : sitemapDtos) {
			Element url = new Element("url", nsSitemap);

			url.addContent(new Element("loc", nsSitemap).addContent(sitemapDto.getLoc()));

			if(sitemapDto.getLastmod() != null) {
				url.addContent(new Element("lastmod", nsSitemap).setText(sdf.format(sitemapDto.getLastmod())));
			}

			if(sitemapDto.getChangefreq() != null) {
				url.addContent(new Element("changefreq", nsSitemap).addContent(sitemapDto.getChangefreq()));
			}

			if(sitemapDto.getPriority() != null) {
				url.addContent(new Element("priority", nsSitemap).setText(sitemapDto.getPriority().toString()));
			}

			root.addContent(url);
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
