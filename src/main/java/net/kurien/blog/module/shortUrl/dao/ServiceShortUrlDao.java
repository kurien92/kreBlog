package net.kurien.blog.module.shortUrl.dao;

import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.entity.ShortUrl;

public interface ServiceShortUrlDao {
	ServiceShortUrl select(String serviceName, int serviceNo);
	void insert(ServiceShortUrl serviceShortUrl);
}
