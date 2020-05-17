package net.kurien.blog.module.category.dao;

import java.util.List;

import net.kurien.blog.module.category.entity.Category;

public interface CategoryDao {
	List<Category> selectList();

	Category select(int CategoryNo);

	Category select(String categoryId);
	
	void insert(Category category);
	
	void update(Category category);
	
	void delete(int categoryNo);

	void delete(String categoryId);

	List<Category> selectListByParentNo(Integer categoryParentNo);
}
