package net.kurien.blog.module.post.dao;

import java.util.List;
import java.util.Map;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.post.entity.Post;

public interface PostDao {
	List<Post> selectList(String manageYn);
	List<Post> selectList(String manageYn, SearchCriteria criteria);
	Post selectOne(int postNo, String manageYn);
	List<Post> selectListByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);
	int selectCount(String manageYn);
	int selectCountByCategoryId(String categoryId, String manageYn, SearchCriteria criteria);
	int selectCountByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);
	void insert(Post post);
	void update(Post post);
	void delete(int postNo);
	void deleteList(List<Integer> postNos);
	void deleteAll();
	int isExist(int postNo, String manageYn);
	int removeCategoryId(String categoryId);
    List<Post> search(String[] queries);
}
