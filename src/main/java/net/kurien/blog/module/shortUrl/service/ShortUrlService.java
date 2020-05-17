package net.kurien.blog.module.shortUrl.service;

import net.kurien.blog.module.shortUrl.entity.ShortUrl;

public interface ShortUrlService {
	public ShortUrl get(int shortUrl);
	public String getRedirectUrl(String shortUrl) throws Exception;
	public void set(ShortUrl shortUrl);
}
