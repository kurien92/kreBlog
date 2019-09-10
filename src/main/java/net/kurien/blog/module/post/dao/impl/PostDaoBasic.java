package net.kurien.blog.module.post.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.domain.Criteria;
import net.kurien.blog.module.post.dao.PostDao;
import net.kurien.blog.module.post.vo.Post;

@Repository
public class PostDaoBasic implements PostDao {
	@Inject
	private SqlSession sqlSession;
	
	private final static String mapper = "net.kurien.blog.module.post.mapper";
	
	@Override
	public List<Post> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapper + ".selectList");
	}

	@Override
	public List<Post> selectList(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapper + ".selectList", criteria);
	}

	@Override
	public List<Post> selectListByCategoryIds(List<String> categoryIds) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapper + ".selectListByCategoryIds", categoryIds);
	}
	
	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectCount");
	}

	@Override
	public int selectCount(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectCount", criteria);
	}

	@Override
	public int selectCountByCategoryIds(List<String> categoryIds) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectCountByCategoryIds", categoryIds);
	}
	
	@Override
	public Post selectOne(int postNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectOne", postNo);
	}

	@Override
	public void insert(Post post) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", post);
	}

	@Override
	public void update(Post post) {
		// TODO Auto-generated method stub
		sqlSession.update(mapper + ".update", post);
	}

	@Override
	public void delete(int postNo) {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper + ".delete", postNo);
	}

	@Override
	public void deleteList(List<Integer> postNos) {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper + ".deleteList", postNos);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper + ".deleteAll");
	}

	@Override
	public int isExist(int postNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".isExist", postNo);
	}

	@Override
	public int removeCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		int updatedCount = sqlSession.update(mapper + ".removeCategoryId", categoryId);
		
		return updatedCount;
	}
}