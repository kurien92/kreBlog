package net.kurien.blog.module.category.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import net.kurien.blog.module.category.dao.CategoryDao;
import net.kurien.blog.module.category.vo.Category;

@Repository
public class CategoryDaoBasic implements CategoryDao {
	@Inject
	private SqlSession sqlSession;
	
	private final static String mapper = "net.kurien.blog.module.category.mapper";
	
	@Override
	public List<Category> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapper + ".selectList");
	}

	@Override
	public List<Category> selectListByParentNo(Integer categoryNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapper + ".selectListByParentNo", categoryNo);
	}
	
	@Override
	public Category select(int categoryNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectOneByNo", categoryNo);
	}
	
	@Override
	public Category select(String categoryId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapper + ".selectOneById", categoryId);
	}
	
	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		sqlSession.insert(mapper + ".insert", category);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		sqlSession.update(mapper + ".update", category);
	}

	@Override
	public void delete(int categoryNo) {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper + ".delete", categoryNo);
	}

	@Override
	public void delete(String categoryId) {
		// TODO Auto-generated method stub
		sqlSession.delete(mapper + ".delete", categoryId);
	}
}
