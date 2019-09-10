package net.kurien.blog.module.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.kurien.blog.module.category.dao.CategoryDao;
import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.module.category.vo.Category;

@Service
public class CategoryServiceBasic implements CategoryService {
	@Inject
	private CategoryDao categoryDaoBasic;

	@Override
	public String createCategory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Category> getList() {
		return categoryDaoBasic.selectList();
	}
	
	public Category get(int categoryNo) {
		return categoryDaoBasic.select(categoryNo);
	}
	
	public Category get(String categoryId) {
		return categoryDaoBasic.select(categoryId);
	}
	
	public List<Category> getCategoryAndChilds(String categoryId) {
		List<Category> categories = new ArrayList<Category>();
		
		Category parentCategory = categoryDaoBasic.select(categoryId);
		
		if(parentCategory == null) {
			return null;
		}
		
		categories.add(parentCategory);
		
		List<Category> childCategories = categoryDaoBasic.selectListByParentNo(parentCategory.getCategoryNo());
		
		categories.addAll(childCategories);
		
		return categories;
	}
	
	public void set(Category category) {
		categoryDaoBasic.insert(category);
	}
	
	public void modify(Category category) {
		categoryDaoBasic.update(category);
	}

	@Override
	public void remove(int categoryNo) {
		// TODO Auto-generated method stub{
		categoryDaoBasic.delete(categoryNo);
	}

	@Override
	public void remove(String categoryId) {
		// TODO Auto-generated method stub
		categoryDaoBasic.delete(categoryId);
	}
}
