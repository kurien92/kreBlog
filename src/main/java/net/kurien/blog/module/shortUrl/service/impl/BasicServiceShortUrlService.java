package net.kurien.blog.module.shortUrl.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kurien.blog.module.shortUrl.dao.ServiceShortUrlDao;
import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.service.ServiceShortUrlService;

@Service
public class BasicServiceShortUrlService implements ServiceShortUrlService {
	private final ServiceShortUrlDao serviceShortUrlDao;

	@Autowired
	public BasicServiceShortUrlService(ServiceShortUrlDao serviceShortUrlDao) {
		this.serviceShortUrlDao = serviceShortUrlDao;
	}
	
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
