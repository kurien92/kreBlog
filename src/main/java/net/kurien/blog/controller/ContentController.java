package net.kurien.blog.controller;

import net.kurien.blog.common.template.Template;
import net.kurien.blog.module.category.entity.Category;
import net.kurien.blog.module.content.entity.Content;
import net.kurien.blog.module.content.service.ContentService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.entity.ShortUrl;
import net.kurien.blog.util.HtmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping(value="/content")
public class ContentController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private Template template;
    private ContentService contentService;

    @Inject
    public ContentController(Template template, ContentService contentService) {
        this.template = template;
        this.contentService = contentService;
    }

    @RequestMapping(value="/{contentId}", method=RequestMethod.GET)
    public String view(@PathVariable String contentId, Model model) {
        Content content = contentService.view(contentId, "N");

        model.addAttribute("content", content);

        template.setSubTitle(content.getContentTitle());
        template.setDescription(HtmlUtil.stripHtml(content.getContent()));
        template.getCss().add("<link rel=\"stylesheet\" href=\"/css/module/content.css\">");

        return "content/view";
    }
}
