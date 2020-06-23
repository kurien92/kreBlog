package net.kurien.blog.module.post.service.impl;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.search.dto.SearchDTO;
import net.kurien.blog.module.search.dto.Searchable;
import org.springframework.stereotype.Service;

import net.kurien.blog.exception.DuplicatedKeyException;
import net.kurien.blog.exception.EmptyParameterException;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.exception.NotUsePrimaryKeyException;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.post.dao.PostDao;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.service.PostService;

@Service
public class BasicPostService implements PostService, Searchable {
	@Inject
	private PostDao postDao;
	
	@Inject
	private ServiceFileService serviceFileService;
	
	@Override
	public List<Post> getList(String manageYn) {
		// TODO Auto-generated method stub
		return postDao.selectList(manageYn);
	}
	
	@Override
	public List<Post> getList(String manageYn, SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return postDao.selectList(manageYn, criteria);
	}

	@Override
	public List<Post> getListByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return postDao.selectListByCategoryIds(categoryIds, manageYn, criteria);
	}
	
	@Override
	public int getCount(String manageYn) {
		// TODO Auto-generated method stub
		return postDao.selectCount(manageYn);
	}

	@Override
	public int getCountByCategoryId(String categoryId, String manageYn) {
		return postDao.selectCountByCategoryId(categoryId, manageYn, null);
	}

	@Override
	public int getCountByCategoryIds(List<String> categoryIds, String manageYn, SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return postDao.selectCountByCategoryIds(categoryIds, manageYn, criteria);
	}
	
	@Override
	public Post get(int postNo, String manageYn) throws Exception {
		// TODO Auto-generated method stub
		if(!isExist(postNo, manageYn)) {
			throw new NotFoundDataException(postNo + "번 포스트를 찾을 수 없습니다.");
		}

		return postDao.selectOne(postNo, manageYn);
	}

	@Override
	public void write(Post post, Integer[] fileNos) throws Exception {
		// TODO Auto-generated method stub
		if(post.getPostNo() != null) {
			if(post.getPostNo() == 0) {
				throw new NotUsePrimaryKeyException("작성될 포스트의 번호가 잘못 입력되었습니다.");
			}
			
			if(isExist(post.getPostNo(), "Y")) {
				throw new DuplicatedKeyException(post.getPostNo() + "번 포스트가 이미 존재합니다.");
			}
		}
		
		postDao.insert(post);

		addServiceFiles(post, fileNos);
	}

	@Override
	public void modify(Post post, Integer[] fileNos) throws Exception {
		// TODO Auto-generated method stub
		if(post.getPostNo() == null || post.getPostNo() == 0) {
			throw new NotUsePrimaryKeyException("수정될 포스트의 번호가 입력되지 않았습니다.");
		}
		
		if(!isExist(post.getPostNo(), "Y")) {
			throw new NotFoundDataException(post.getPostNo() + "번 포스트를 찾을 수 없습니다.");
		}

		postDao.update(post);

		addServiceFiles(post, fileNos);
	}

	private void addServiceFiles(Post post, Integer[] fileNos) {
		if(fileNos != null) {
			serviceFileService.addFiles("post", post.getPostNo(), fileNos, post.getPostWriteIp());
		}

		Set<Integer> useFilesNo = new HashSet<>();

		Pattern pattern = Pattern.compile("['|\"]/file/viewer/post/(\\d+?)['|\"]");
		Matcher matcher = pattern.matcher(post.getPostContent());

		while(matcher.find()) {
			useFilesNo.add(Integer.parseInt(matcher.group(1)));
		}

		serviceFileService.syncFiles("post", post.getPostNo(), useFilesNo, post.getPostWriteIp());
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
	public boolean isExist(int postNo, String manageYn) throws Exception {
		// TODO Auto-generated method stub
		int count = postDao.isExist(postNo, manageYn);
		
		if(count < 1) {
			return false;
		}
		
		if(count > 1) {
			throw new DuplicatedKeyException(postNo + "번의 자료가 " + count + "개 중복 데이터가 존재합니다.");
		}
		
		return true;
	}

	@Override
	public int removeCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		return postDao.removeCategoryId(categoryId);
	}

	@Override
	public SearchDTO search(String[] queries) {
		List<Map<String, Object>> contents = new ArrayList<>();
		SearchDTO searchDto = new SearchDTO();

		List<Post> posts = postDao.search(queries);

		for(Post post : posts) {
			Map<String, Object> content = new LinkedHashMap<>();

			content.put("id", post.getPostNo());
			content.put("name", post.getPostAuthor());
			content.put("title", post.getPostSubject());
			content.put("description", post.getPostContent());

			contents.add(content);
		}

		searchDto.setTitle("POST");
		searchDto.setContents(contents);

		return searchDto;
	}
}