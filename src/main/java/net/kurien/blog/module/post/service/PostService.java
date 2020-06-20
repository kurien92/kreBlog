package net.kurien.blog.module.post.service;

import java.util.List;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.post.entity.Post;

public interface PostService {
	public List<Post> getList(String manageYn);
	public List<Post> getList(String manageYn, SearchCriteria criteria);
	
	public Post get(int postNo, String manageYn) throws Exception;
	
	public List<Post> getListByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);
	
	public int getCount(String manageYn);
	public int getCountByCategoryId(String categoryId, String manageYn);
	public int getCountByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);

	public void write(Post post, Integer[] fileNos) throws Exception;
	public void modify(Post post, Integer[] fileNos) throws Exception;
	public void delete(int postNo);
	public void deleteList(List<Integer> postNos) throws Exception;
	public void deleteAll();
	public int removeCategoryId(String categoryId) throws Exception;
	
	public boolean isExist(int postNo, String manageYn) throws Exception;
}
