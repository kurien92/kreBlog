package net.kurien.blog.module.visitor.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.comment.entity.Comment;
import net.kurien.blog.module.visitor.entity.Visitor;

@Repository
public class BasicVisitorDao implements VisitorDao {
	@Inject
	private SqlSession sqlSession;
	
	private final static String mapper = "net.kurien.blog.module.visitor.mapper";

	@Override
	public void insert(Visitor visitor) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", visitor);
	}

}
