package net.kurien.blog.database.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisTestDao {
	@Inject
	private SqlSession sqlSession;
	
	public String selectOne(String name) {
		return sqlSession.selectOne("query.select", name);
	}
	
	public List<String> selectList(String name) {
		return sqlSession.selectList("query.selectList", name);
	}
}
