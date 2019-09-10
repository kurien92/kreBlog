package net.kurien.blog.module.post.dao;

import java.util.List;

import net.kurien.blog.domain.Criteria;
import net.kurien.blog.module.post.vo.Post;

public interface PostDao {
	public List<Post> selectList();
	public List<Post> selectList(Criteria criteria);
	public int selectCount();
	public int selectCount(Criteria criteria);
	public Post selectOne(int postNo);
	public void insert(Post post);
	public void update(Post post);
	public void delete(int postNo);
	public void deleteList(List<Integer> postNos);
	public void deleteAll();
	public int isExist(int postNo);
	public int removeCategoryId(String categoryId);
	
	public int selectCountByCategoryIds(List<String> categoryIds);
	public List<Post> selectListByCategoryIds(List<String> categoryIds);
}
