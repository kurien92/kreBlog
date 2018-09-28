package net.kurien.blog.module.post.service;

import java.util.List;

import net.kurien.blog.domain.Criteria;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.post.vo.Post;

public interface PostService {
	public List<Post> getList();
	public List<Post> getList(Criteria criteria);
	public int getCount();
	public int getCount(Criteria criteria);
	public Post get(int postNo) throws Exception;
	public void write(Post post) throws Exception;
	public void modify(Post post) throws Exception;
	public void delete(int postNo);
	public void deleteList(List<Integer> postNos) throws Exception;
	public void deleteAll();
	
	public boolean isExist(int postNo) throws Exception;
}
