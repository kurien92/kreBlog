package net.kurien.blog.module.sitemap;

import lombok.Data;

import java.util.Date;

@Data
public class SitemapDTO {
    private String loc;
    private Date lastmod;
    private String changefreq;
    private Double priority;
}
