package net.kurien.blog.module.shortUrl.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.shortUrl.dao.ServiceShortUrlDao;
import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;
import net.kurien.blog.module.shortUrl.entity.ShortUrl;

@Repository
public class BasicServiceShortUrlDao implements ServiceShortUrlDao {
	@Inject
	private SqlSession sqlSession;
	
	private final static String mapper = "net.kurien.blog.module.serviceShortUrl.mapper";

	@Override
	public ServiceShortUrl select(String serviceName, int serviceNo) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("serviceName", serviceName);
		param.put("serviceNo", serviceNo);
		
		return sqlSession.selectOne(mapper + ".selectOne", param);
	}

	@Override
	public void insert(ServiceShortUrl serviceShortUrl) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", serviceShortUrl);
	}
	
}
