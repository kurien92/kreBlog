package net.kurien.blog.module.sitemap;

import java.util.List;

public interface SitemapCreatable {
    List<SitemapDTO> sitemap(String siteUrl);
}
