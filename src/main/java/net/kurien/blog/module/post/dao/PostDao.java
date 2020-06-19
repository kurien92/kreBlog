package net.kurien.blog.module.post.dao;

import java.util.List;
import java.util.Map;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.post.entity.Post;

public interface PostDao {
	public List<Post> selectList(String manageYn);
	public List<Post> selectList(String manageYn, SearchCriteria criteria);
	
	public Post selectOne(int postNo, String manageYn);
	
	public List<Post> selectListByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);

	public int selectCount(String manageYn);

	public int selectCountByCategoryId(String categoryId, String manageYn, SearchCriteria criteria);
	public int selectCountByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria);
	
	public void insert(Post post);
	public void update(Post post);
	public void delete(int postNo);
	public void deleteList(List<Integer> postNos);
	public void deleteAll();
	public int isExist(int postNo, String manageYn);
	public int removeCategoryId(String categoryId);

    public List<Post> search(String[] keywords);
}
