package net.kurien.blog.module.comment.dao;

import java.util.List;

import net.kurien.blog.module.comment.entity.Comment;

public interface CommentDao {
	List<Comment> selectList();
	List<Comment> selectListByPostNo(int postNo);
	Comment selectOne(int commentNo);
	Integer getLastOrder(int parentCommentNo, int commentDepth);
	void insert(Comment comment);
	void update(Comment comment);
	void delete(int commentNo);
    List<Comment> search(String[] queries);
}
