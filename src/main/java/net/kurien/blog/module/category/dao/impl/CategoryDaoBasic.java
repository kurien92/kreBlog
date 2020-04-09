package net.kurien.blog.module.category.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Category> selectListByParentNo(Integer categoryParentNo) {
		// TODO Auto-generated method stub
		// Integer를 파라미터로 사용하는 경우에는 Mapper에서 변수명을 value로 사용해야한다.
		// value로 사용하는 경우 가독성이 떨어질 것으로 생각되어 Map에 추가하여 파라미터 이름을 지정.
		Map<String, Object> param = new HashMap<>();
		param.put("categoryParentNo", categoryParentNo);
		
		return sqlSession.selectList(mapper + ".selectListByParentNo", param);
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
