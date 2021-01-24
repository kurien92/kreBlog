package net.kurien.blog.module.sitemap;

import lombok.Data;

import java.util.Date;

@Data
public class SitemapDto {
    private String loc;
    private Date lastmod;
    private String changefreq;
    private Double priority;
}
