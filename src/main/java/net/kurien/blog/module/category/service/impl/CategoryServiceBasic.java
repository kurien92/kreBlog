package net.kurien.blog.module.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.kurien.blog.module.category.dao.CategoryDao;
import net.kurien.blog.module.category.service.CategoryService;
import net.kurien.blog.module.category.vo.Category;
import net.kurien.blog.module.post.service.PostService;

@Service
public class CategoryServiceBasic implements CategoryService {
	@Inject
	private CategoryDao categoryDaoBasic;

	@Inject
	private PostService postService;

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
	
	public void create(Category category) {
		int categoryDepth = createCategoryDepth(category.getCategoryParentNo());
		category.setCategoryDepth(categoryDepth);
		
		categoryDaoBasic.insert(category);
	}
	
	public void modify(Category category) {
		int categoryDepth = createCategoryDepth(category.getCategoryParentNo());
		category.setCategoryDepth(categoryDepth);
		
		categoryDaoBasic.update(category);
	}

	@Override
	public void remove(int categoryNo) {
		// TODO Auto-generated method stub{
		categoryDaoBasic.delete(categoryNo);
	}

//	@Transactional 추후에 적용
	@Override
	public void remove(String categoryId) {
		// TODO Auto-generated method stub
		try {
			int removedCount = postService.removeCategoryId(categoryId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		categoryDaoBasic.delete(categoryId);
	}
	
	private int createCategoryDepth(Integer parentCategryNo) {
		Integer categoryDepth = 0;
		
		if(parentCategryNo != null) {
			Category parentCategory = categoryDaoBasic.select(parentCategryNo);
			categoryDepth = parentCategory.getCategoryDepth() + 1;
		}
		
		return categoryDepth;
	}
}
