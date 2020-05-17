package net.kurien.blog.module.comment.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.comment.dao.CommentDao;
import net.kurien.blog.module.comment.entity.Comment;

@Repository
public class BasicCommentDao implements CommentDao {
	@Inject
	private SqlSession sqlSession;
	
	private final static String mapper = "net.kurien.blog.module.comment.mapper";
	
	@Override
	public List<Comment> selectList(int postNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapper + ".selectList", postNo);
	}

	@Override
	public Comment selectOne(int commentNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectOne", commentNo);
	}

	@Override
	public Integer getLastOrder(int parentCommentNo, int commentDepth) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("parentCommentNo", parentCommentNo);
		param.put("commentDepth", commentDepth);

		return sqlSession.selectOne(mapper + ".getLastOrder", param);
	}

	@Override
	public void insert(Comment comment) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", comment);
	}

	@Override
	public void update(Comment comment) {
		// TODO Auto-generated method stub
		sqlSession.update(mapper + ".update", comment);
	}

	@Override
	public void delete(int commentNo) {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper + ".delete", commentNo);
	}
}
