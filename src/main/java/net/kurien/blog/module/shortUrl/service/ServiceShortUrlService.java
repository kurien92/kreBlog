package net.kurien.blog.module.shortUrl.service;

import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;

public interface ServiceShortUrlService {
	public ServiceShortUrl get(String serviceName, int serviceNo);
	public void add(ServiceShortUrl serviceShortUrl);
}
