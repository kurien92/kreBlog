package net.kurien.blog.module.shortUrl.dao;

import net.kurien.blog.module.shortUrl.entity.ShortUrl;

public interface ShortUrlDao {

	ShortUrl select(int shortUrlNo);

	void visitCount(int shortUrlNo);

	void insert(ShortUrl shortUrl);

	void setEncoded(int shortUrlNo, String encodedUrl);

	int getCount(int shortUrlNo);
}
