package net.kurien.blog.module.shortUrl.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.shortUrl.dao.ServiceShortUrlDao;
import net.kurien.blog.module.shortUrl.entity.ServiceShortUrl;

@Repository
public class BasicServiceShortUrlDao implements ServiceShortUrlDao {
	private final SqlSession sqlSession;
	private final static String mapper = "net.kurien.blog.module.shortUrl.mapper.ServiceShortUrl";

	@Autowired
	public BasicServiceShortUrlDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

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
