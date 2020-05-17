package net.kurien.blog.module.category.service;

import java.util.List;

import net.kurien.blog.module.category.entity.Category;

public interface CategoryService {
	List<Category> getList();
	
	Category get(int categoryNo);
	
	Category get(String categoryId);
	
	void create(Category category);
	
	void modify(Category category);
	
	void remove(int categoryNo);
	
	void remove(String categoryId);

	List<Category> getCategoryAndChilds(String categoryId);

	String getCategoryHTML(String contextPath);
}
