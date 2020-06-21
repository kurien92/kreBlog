package net.kurien.blog.common.template;

import lombok.Data;
import org.springframework.stereotype.Component;

import net.kurien.blog.common.template.metadata.TemplateCss;
import net.kurien.blog.common.template.metadata.TemplateJs;
import net.kurien.blog.common.template.metadata.TemplateMeta;

@Component
@Data
public class Template {
	private String lang;
	private String charset;
	private TemplateMeta meta;
	private String title;
	private String mainTitle;
	private String subTitle;
	private String description;
	private TemplateCss css;
	private TemplateJs headJs;
	private TemplateJs footJs;
	private String categoryHTML;
	private String searchQuery;
}