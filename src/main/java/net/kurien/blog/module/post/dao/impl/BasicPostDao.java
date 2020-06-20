package net.kurien.blog.module.post.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.kurien.blog.domain.SearchCriteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.post.dao.PostDao;
import net.kurien.blog.module.post.entity.Post;

@Repository
public class BasicPostDao implements PostDao {
	@Inject
	private SqlSession sqlSession;
	
	private final static String mapper = "net.kurien.blog.module.post.mapper";
	
	@Override
	public List<Post> selectList(String manageYn, SearchCriteria criteria) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("manageYn", manageYn);
		param.put("criteria", criteria);
		
		return sqlSession.selectList(mapper + ".selectList", param);
	}

	@Override
	public List<Post> selectListByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("categoryIds", categoryIds);
		param.put("manageYn", manageYn);
		param.put("criteria", criteria);
		
		return sqlSession.selectList(mapper + ".selectListByCategoryIds", param);
	}

	@Override
	public List<Post> selectList(String manageYn) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("manageYn", manageYn);
		
		return sqlSession.selectList(mapper + ".selectList", param);
	}
	
	@Override
	public int selectCount(String manageYn) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("manageYn", manageYn);
		
		return sqlSession.selectOne(mapper + ".selectCount", param);		
	}
	
	@Override
	public Post selectOne(int postNo, String manageYn) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("postNo", postNo);
		param.put("manageYn", manageYn);
		
		return sqlSession.selectOne(mapper + ".selectOne", param);
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
	public int removeCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		return sqlSession.update(mapper + ".removeCategoryId", categoryId);
	}

	@Override
	public int isExist(int postNo, String manageYn) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("postNo", postNo);
		param.put("manageYn", manageYn);
		
		return sqlSession.selectOne(mapper + ".isExist", param);
	}

	@Override
	public int selectCountByCategoryId(String categoryId, String manageYn, SearchCriteria criteria) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("categoryId", categoryId);
		param.put("manageYn", manageYn);
		param.put("criteria", criteria);
		
		return sqlSession.selectOne(mapper + ".selectCountByCategoryId", param);
	}

	@Override
	public int selectCountByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<>();
		param.put("categoryIds", categoryIds);
		param.put("manageYn", manageYn);
		param.put("criteria", criteria);
		
		return sqlSession.selectOne(mapper + ".selectCountByCategoryIds", param);
	}
}