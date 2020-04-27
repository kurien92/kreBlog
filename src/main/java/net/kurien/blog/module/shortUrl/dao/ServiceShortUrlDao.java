package net.kurien.blog.module.shortUrl.dao;

import net.kurien.blog.module.shortUrl.vo.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.vo.ShortUrl;

public interface ServiceShortUrlDao {
	ServiceShortUrl select(String serviceName, int serviceNo);
	void insert(ServiceShortUrl serviceShortUrl);
}
