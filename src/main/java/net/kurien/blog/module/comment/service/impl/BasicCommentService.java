package net.kurien.blog.module.comment.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.comment.dao.CommentDao;
import net.kurien.blog.module.comment.entity.Comment;
import net.kurien.blog.module.comment.service.CommentService;

@Service
public class BasicCommentService implements CommentService {
	@Inject
	private CommentDao commentDao;

	@Override
	public List<Comment> getList() {
		// TODO Auto-generated method stub
		return commentDao.selectList();
	}
	
	@Override
	public List<Comment> getList(int postNo) {
		// TODO Auto-generated method stub
		return commentDao.selectListByPostNo(postNo);
	}

	@Override
	public Comment get(int commentNo) {
		// TODO Auto-generated method stub
		return commentDao.selectOne(commentNo);
	}

	@Override
	public void write(Comment comment) {
		// TODO Auto-generated method stub
		comment.setCommentOrder(1);
		comment.setCommentDepth(1);
		comment.setDeleteYn("N");
		comment.setWriteTime(Calendar.getInstance().getTime());
		comment.setPassword(hashPassword(comment.getPassword()));
		
		commentDao.insert(comment);
		
		Comment updateComment = new Comment();

		updateComment.setCommentNo(comment.getCommentNo());
		updateComment.setParentCommentNo(comment.getCommentNo());
		
		commentDao.update(updateComment);
	}
	
	public void reply(int parentCommentNo, Comment comment) throws InvalidRequestException {
		Comment parentComment = commentDao.selectOne(parentCommentNo);
		
		int commentDepth = parentComment.getCommentDepth() + 1;
		
		if(commentDepth > 2) {
			throw new InvalidRequestException("잘못된 요청입니다.");
		}

		int commentOrder = getLastOrder(parentComment.getCommentNo(), commentDepth);
		
		comment.setParentCommentNo(parentComment.getParentCommentNo());
		comment.setPostNo(parentComment.getPostNo());
		comment.setCommentOrder(commentOrder);
		comment.setCommentDepth(commentDepth);
		comment.setDeleteYn("N");
		comment.setWriteTime(Calendar.getInstance().getTime());
		comment.setPassword(hashPassword(comment.getPassword()));
		
		commentDao.insert(comment);
	}

	@Override
	public void modify(Comment comment) {
		// TODO Auto-generated method stub
		comment.setPassword(hashPassword(comment.getPassword()));			
		
		commentDao.update(comment);
	}

	@Override
	public void delete(int commentNo) {
		// TODO Auto-generated method stub
		Comment comment = new Comment();
		
		comment.setCommentNo(commentNo);
		comment.setDeleteYn("Y");
		
		commentDao.update(comment);
	}

	@Override
	public void remove(int commentNo) {
		// TODO Auto-generated method stub
		commentDao.delete(commentNo);
	}
	
	/**
	 * 입력된 Password가 해당 댓글의 Password가 맞는지 검증한다.
	 * 
	 * @param commentNo
	 * @param password
	 * @return
	 */
	public boolean checkPassword(int commentNo, String password) {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

		Comment comment = commentDao.selectOne(commentNo);

		return bcryptPasswordEncoder.matches(password, comment.getPassword());
	}
	
	/**
	 * 비밀번호를 hash화 한다.
	 * 
	 * @param password
	 * @return
	 */
	private String hashPassword(String password) {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = bcryptPasswordEncoder.encode(password);
		
		return encodedPassword;
	}
	
	private int getLastOrder(int parentCommentNo, int commentDepth) {
		Integer commentOrder = commentDao.getLastOrder(parentCommentNo, commentDepth);
		
		if(commentOrder == null) {
			commentOrder = 1;
		}
		
		return commentOrder + 1;
	}
}
