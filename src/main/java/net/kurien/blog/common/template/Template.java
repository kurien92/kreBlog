package net.kurien.blog.common.template;

import org.springframework.stereotype.Component;

import net.kurien.blog.common.template.metadata.TemplateCss;
import net.kurien.blog.common.template.metadata.TemplateJs;
import net.kurien.blog.common.template.metadata.TemplateMeta;

@Component
public class Template {
	private String lang;
	private String charset;
	private TemplateMeta meta;
	private String title;
	private TemplateCss css;
	private TemplateJs headJs;
	private TemplateJs footJs;
	private String categoryHTML;
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public TemplateMeta getMeta() {
		return meta;
	}

	public void setMeta(TemplateMeta meta) {
		this.meta = meta;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TemplateCss getCss() {
		return css;
	}
	
	public void setCss(TemplateCss css) {
		this.css = css;
	}

	public TemplateJs getHeadJs() {
		return headJs;
	}

	public void setHeadJs(TemplateJs headJs) {
		this.headJs = headJs;
	}

	public TemplateJs getFootJs() {
		return footJs;
	}

	public void setFootJs(TemplateJs footJs) {
		this.footJs = footJs;
	}

	public String getCategoryHTML() {
		return categoryHTML;
	}

	public void setCategoryHTML(String categoryHTML) {
		this.categoryHTML = categoryHTML;
	}

	@Override
	public String toString() {
		return "Template [lang=" + lang + ", charset=" + charset + ", meta=" + meta + ", title=" + title + ", css="
				+ css + ", headJs=" + headJs + ", footJs=" + footJs + ", categoryHTML=" + categoryHTML + "]";
	}
}