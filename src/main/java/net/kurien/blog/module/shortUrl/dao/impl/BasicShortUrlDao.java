package net.kurien.blog.module.shortUrl.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.shortUrl.dao.ShortUrlDao;
import net.kurien.blog.module.shortUrl.entity.ShortUrl;

@Repository
public class BasicShortUrlDao implements ShortUrlDao {
	private final SqlSession sqlSession;
	private final static String mapper = "net.kurien.blog.module.shortUrl.mapper.ShortUrlMapper";

	@Autowired
	public BasicShortUrlDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public ShortUrl select(int shortUrlNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectOne", shortUrlNo);
	}

	@Override
	public void visitCount(int shortUrlNo) {
		// TODO Auto-generated method stub
		sqlSession.update(mapper + ".visitCount", shortUrlNo);
	}

	@Override
	public void insert(ShortUrl shortUrl) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", shortUrl);
	}
	
	@Override
	public void setEncoded(int shortUrlNo, String encodedUrl) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("shortUrlNo", shortUrlNo);
		param.put("encodedUrl", encodedUrl);
		
		sqlSession.update(mapper + ".setEncoded", param);
	}

	@Override
	public int getCount(int shortUrlNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectCount", shortUrlNo);
	}
	
}
