package net.kurien.blog.module.post.service;

import java.util.List;

import net.kurien.blog.module.post.vo.Post;

public interface PostService {
	public List<Post> getList();
	public int getCount();
	public Post get(int postNo);
	public void write(Post post) throws Exception;
	public void modify(Post post) throws Exception;
	public void delete(int postNo);
	public void deleteList(List<Integer> postNos) throws Exception;
	public void deleteAll();
	
	public boolean isExist(int postNo) throws Exception;
}
