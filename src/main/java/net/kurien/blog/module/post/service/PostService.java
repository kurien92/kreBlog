package net.kurien.blog.module.post.service;

import java.util.List;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.post.entity.Post;

public interface PostService {
	List<Post> getList(String manageYn);
	List<Post> getList(String manageYn, SearchCriteria criteria);
	
	Post get(int postNo, String manageYn) throws Exception;
	
	List<Post> getListByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);
	
	int getCount(String manageYn);
	int getCountByCategoryId(String categoryId, String manageYn);
	int getCountByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);

	void write(Post post, Integer[] fileNos) throws Exception;
	void modify(Post post, Integer[] fileNos) throws Exception;
	void delete(int postNo);
	void deleteList(List<Integer> postNos) throws Exception;
	void deleteAll();
	int removeCategoryId(String categoryId) throws Exception;
	
	boolean isExist(int postNo, String manageYn) throws Exception;
}
