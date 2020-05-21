package net.kurien.blog.module.comment.service;

import java.util.List;

import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.comment.entity.Comment;

public interface CommentService {
    public List<Comment> getList();
    public List<Comment> getList(int postNo);
    public Comment get(int commentNo);
    public void write(Comment comment);
    public void reply(int parentCommentNo, Comment comment) throws InvalidRequestException;
    public void modify(Comment comment);
    public void delete(int commentNo);
    public void remove(int commentNo);
	public boolean checkPassword(int no, String password);
}
