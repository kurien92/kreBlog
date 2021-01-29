package net.kurien.blog.module.comment.service;

import java.util.List;

import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.comment.entity.Comment;

public interface CommentService {
    List<Comment> getList();
    List<Comment> getList(int postNo);
    Comment get(int commentNo);
    void write(Comment comment);
    void reply(int parentCommentNo, Comment comment) throws InvalidRequestException;
    void modify(Comment comment);
    void delete(int commentNo);
    void remove(int commentNo);
	boolean checkPassword(int commentNo, String password);
    boolean checkUser(int commentNo, int accountNo);
}
