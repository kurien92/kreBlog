package net.kurien.blog.database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisTestDao {
	private final SqlSession sqlSession;

	@Autowired
	public MybatisTestDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public String selectOne(String name) {
		return sqlSession.selectOne("query.select", name);
	}
	
	public List<String> selectList(String name) {
		return sqlSession.selectList("query.selectList", name);
	}
}
