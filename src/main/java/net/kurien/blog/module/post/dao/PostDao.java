package net.kurien.blog.module.post.dao;

import java.util.List;
import java.util.Map;

import net.kurien.blog.module.post.vo.Post;

public interface PostDao {
	public List<Post> selectList();
	public List<Post> selectList(Map<String, Object> param);
	public int selectCount();
	public int selectCount(Map<String, Object> param);
	public Post selectOne(int postNo);
	public void insert(Post post);
	public void update(Post post);
	public void delete(int postNo);
	public void deleteList(List<Integer> postNos);
	public void deleteAll();
}
