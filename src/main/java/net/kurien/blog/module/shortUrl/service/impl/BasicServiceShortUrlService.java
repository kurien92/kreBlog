package net.kurien.blog.module.shortUrl.service.impl;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.kurien.blog.module.shortUrl.dao.ServiceShortUrlDao;
import net.kurien.blog.module.shortUrl.dao.ShortUrlDao;
import net.kurien.blog.module.shortUrl.service.ServiceShortUrlService;
import net.kurien.blog.module.shortUrl.service.ShortUrlService;
import net.kurien.blog.module.shortUrl.vo.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.vo.ShortUrl;
import net.kurien.blog.util.Base62Util;

@Service
public class BasicServiceShortUrlService implements ServiceShortUrlService {
	@Inject
	private ServiceShortUrlDao serviceShortUrlDao;
	
	@Override
	public ServiceShortUrl get(String serviceName, int serviceNo) {
		// TODO Auto-generated method stub
		return serviceShortUrlDao.select(serviceName, serviceNo);
	}

	@Override
	public void add(ServiceShortUrl serviceShortUrl) {
		// TODO Auto-generated method stub
		serviceShortUrl.setCreateTime(Calendar.getInstance().getTime());
		
		serviceShortUrlDao.insert(serviceShortUrl);
	}
}
