package net.kurien.blog.module.shortUrl.service.impl;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.kurien.blog.module.shortUrl.dao.ShortUrlDao;
import net.kurien.blog.module.shortUrl.service.ShortUrlService;
import net.kurien.blog.module.shortUrl.vo.ShortUrl;
import net.kurien.blog.util.Base62Util;

@Service
public class BasicShortUrlService implements ShortUrlService {
	@Inject
	private ShortUrlDao shortUrlDao;
	
	@Override
	public ShortUrl get(int shortUrlNo) {
		// TODO Auto-generated method stub
		return shortUrlDao.select(shortUrlNo);
	}

	@Override
	public String getRedirectUrl(String encodedUrl) throws Exception {
		// TODO Auto-generated method stub
		int shortUrlNo = (int)Base62Util.decode(encodedUrl);
		
		if(!isExist(shortUrlNo)) {
//			throw new NotFoundRedirectUrlException("존재하지 않거나 삭제된 URL입니다.");
			throw new Exception("존재하지 않거나 삭제된 URL입니다.");
		}
		
		ShortUrl shortUrl = get(shortUrlNo);
		visitCount(shortUrl.getShortUrlNo());
		
		return shortUrl.getRealUrl();
	}
	
	@Override
	public void set(ShortUrl shortUrl) {
		// TODO Auto-generated method stub
		shortUrl.setCreateTime(Calendar.getInstance().getTime());
		
		shortUrlDao.insert(shortUrl);
		
		setEncoded(shortUrl.getShortUrlNo());
	}
	
	private void setEncoded(int shortUrlNo) {
		// TODO Auto-generated method stub
		String encodedUrl = Base62Util.encode(shortUrlNo);
		
		shortUrlDao.setEncoded(shortUrlNo, encodedUrl);
	}

	private void visitCount(int shortUrlNo) {
		shortUrlDao.visitCount(shortUrlNo);
	}

	private boolean isExist(int shortUrlNo) {
		// TODO Auto-generated method stub
		int count = shortUrlDao.getCount(shortUrlNo);
		
		boolean exist = count > 0 ? true : false;
		
		return exist;
	}
}
