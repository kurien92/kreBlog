package net.kurien.blog.module.category.service;

import java.util.List;

import net.kurien.blog.module.category.vo.Category;

public interface CategoryService {
	String createCategory();
	
	List<Category> getList();
	
	Category get(int categoryNo);
	
	Category get(String categoryId);
	
	void set(Category category);
	
	void modify(Category category);
	
	void remove(int categoryNo);
	
	void remove(String categoryId);
}
