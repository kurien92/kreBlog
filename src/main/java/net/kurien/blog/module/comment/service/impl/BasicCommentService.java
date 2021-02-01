package net.kurien.blog.module.comment.service.impl;

import java.util.*;

import net.kurien.blog.module.search.dto.SearchDto;
import net.kurien.blog.module.search.dto.Searchable;
import net.kurien.blog.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.comment.dao.CommentDao;
import net.kurien.blog.module.comment.entity.Comment;
import net.kurien.blog.module.comment.service.CommentService;

@Service
public class BasicCommentService implements CommentService, Searchable {
	private final CommentDao commentDao;

	@Autowired
	public BasicCommentService(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

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

		if(comment.getPassword() != null) {
			comment.setPassword(hashPassword(comment.getPassword()));
		}

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

		if(comment.getPassword() != null) {
			comment.setPassword(hashPassword(comment.getPassword()));
		}

		commentDao.insert(comment);
	}

	@Override
	public void modify(Comment comment) {
		// TODO Auto-generated method stub
		if(comment.getPassword() != null) {
			comment.setPassword(hashPassword(comment.getPassword()));
		}

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
		Comment comment = commentDao.selectOne(commentNo);

		return EncryptionUtil.checkPassword(password, comment.getPassword());
	}

	@Override
	public boolean checkUser(int commentNo, int accountNo) {
		Comment comment = commentDao.selectOne(commentNo);

		if(comment.getAccountNo() == null) {
			throw new NullPointerException("익명으로 작성된 댓글은 로그인된 사용자가 수정/삭제할 수 없습니다.");
		}

		return comment.getAccountNo() == accountNo;
	}

	/**
	 * 비밀번호를 hash화 한다.
	 * 
	 * @param password
	 * @return
	 */
	private String hashPassword(String password) {
		return EncryptionUtil.hashPassword(password);
	}
	
	private int getLastOrder(int parentCommentNo, int commentDepth) {
		Integer commentOrder = commentDao.getLastOrder(parentCommentNo, commentDepth);
		
		if(commentOrder == null) {
			commentOrder = 1;
		}
		
		return commentOrder + 1;
	}

	@Override
	public SearchDto search(String[] queries) {
		List<Map<String, Object>> contents = new ArrayList<>();
		SearchDto searchDto = new SearchDto();

		List<Comment> comments = commentDao.search(queries);

		for(Comment comment : comments) {
			Map<String, Object> content = new LinkedHashMap<>();

			content.put("id", comment.getCommentNo());
			content.put("name", comment.getAuthor());
			content.put("title", comment.getComment());
			content.put("description", null);

			contents.add(content);
		}

		searchDto.setTitle("COMMENT");
		searchDto.setContents(contents);

		return searchDto;
	}
}
