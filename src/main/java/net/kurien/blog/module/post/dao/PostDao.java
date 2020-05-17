package net.kurien.blog.module.post.dao;

import java.util.List;

import net.kurien.blog.domain.Criteria;
import net.kurien.blog.module.post.entity.Post;

public interface PostDao {
	public List<Post> selectList(String manageYn);
	public List<Post> selectList(String manageYn, Criteria criteria);
	
	public Post selectOne(int postNo, String manageYn);
	
	public List<Post> selectListByCategoryIds(List<String> categoryIds, String manageYn);

	public int selectCount(String manageYn);
	public int selectCount(String manageYn, Criteria criteria);

	public int selectCountByCategoryId(String categoryId, String manageYn);
	public int selectCountByCategoryIds(List<String> categoryIds, String manageYn);
	
	public void insert(Post post);
	public void update(Post post);
	public void delete(int postNo);
	public void deleteList(List<Integer> postNos);
	public void deleteAll();
	public int isExist(int postNo, String manageYn);
	public int removeCategoryId(String categoryId);
}
