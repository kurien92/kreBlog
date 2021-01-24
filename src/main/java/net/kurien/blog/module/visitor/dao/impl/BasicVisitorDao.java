package net.kurien.blog.module.visitor.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.visitor.dao.VisitorDao;
import net.kurien.blog.module.visitor.entity.Visitor;

@Repository
public class BasicVisitorDao implements VisitorDao {
	private final SqlSession sqlSession;
	private final static String mapper = "net.kurien.blog.module.visitor.mapper.VisitorMapper";

	@Autowired
	public BasicVisitorDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insert(Visitor visitor) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", visitor);
	}

	public List<Visitor> selectList(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapper + ".selectList", criteria);
	}

	@Override
	public Integer getCount(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectCount", criteria);
	}
}
