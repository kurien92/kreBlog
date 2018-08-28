package net.kurien.blog.module.post.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import net.kurien.blog.domain.Criteria;
import net.kurien.blog.exception.DuplicatedKeyException;
import net.kurien.blog.exception.EmptyParameterException;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.exception.NotUsePrimaryKeyException;
import net.kurien.blog.module.post.dao.PostDao;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.post.vo.Post;

@Service
public class BasicPostService implements PostService {
	@Inject
	private PostDao postDao;
	
	@Override
	public List<Post> getList() {
		// TODO Auto-generated method stub
		return postDao.selectList();
	}
	
	@Override
	public List<Post> getList(Criteria criteria) {
		// TODO Auto-generated method stub
		return postDao.selectList(criteria);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return postDao.selectCount();
	}

	@Override
	public int getCount(Criteria criteria) {
		// TODO Auto-generated method stub
		return postDao.selectCount(criteria);
	}

	@Override
	public Post get(int postNo) {
		// TODO Auto-generated method stub
		return postDao.selectOne(postNo);
	}

	@Override
	public void write(Post post) throws Exception {
		// TODO Auto-generated method stub
		if(post.getPostNo() != null) {
			if(post.getPostNo() == 0) {
				throw new NotUsePrimaryKeyException("작성될 포스트의 번호가 잘못 입력되었습니다.");
			}
			
			if(isExist(post.getPostNo())) {
				throw new DuplicatedKeyException(post.getPostNo() + "번 포스트가 이미 존재합니다.");
			}
		}
		
		postDao.insert(post);
	}

	@Override
	public void modify(Post post) throws Exception {
		// TODO Auto-generated method stub
		if(post.getPostNo() == null || post.getPostNo() == 0) {
			throw new NotUsePrimaryKeyException("수정될 포스트의 번호가 입력되지 않았습니다.");
		}
		
		if(!isExist(post.getPostNo())) {
			throw new NotFoundDataException(post.getPostNo() + "번 포스트를 찾을 수 없습니다.");
		}
		
		postDao.update(post);
	}

	@Override
	public void delete(int postNo) {
		// TODO Auto-generated method stub
		postDao.delete(postNo);
	}

	@Override
	public void deleteList(List<Integer> postNos) throws EmptyParameterException {
		// TODO Auto-generated method stub
		if(postNos == null || postNos.size() == 0) {
			throw new EmptyParameterException("빈 데이터가 전달되었습니다.");
		}
		
		postDao.deleteList(postNos);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		postDao.deleteAll();
	}

	@Override
	public boolean isExist(int postNo) throws Exception {
		// TODO Auto-generated method stub
		int count = postDao.isExist(postNo);
		
		if(count < 1) {
			return false;
		}
		
		if(count > 1) {
			throw new DuplicatedKeyException(postNo + "번의 자료가 " + count + "개 중복 데이터가 존재합니다.");
		}
		
		return true;
	}
}