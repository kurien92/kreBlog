package net.kurien.blog.module.post.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.kurien.blog.exception.DuplicatedKeyException;
import net.kurien.blog.exception.EmptyParameterException;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.exception.NotUsePrimaryKeyException;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.entity.PostPublishStatus;
import net.kurien.blog.module.post.entity.PostViewStatus;
import net.kurien.blog.module.post.service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class PostServiceTest {
	@Inject
	private PostService postService;
	
	@Inject
	private ServiceFileService serviceFileService;
	
	private Post post1 = null;
	private Post post2 = null;
	private Post post3 = null;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Before
	public void setup() throws Exception {
		postService.deleteAll();
		
		assertThat(postService.getCount("N"), is(0));

		serviceFileService.removeAll();
		
		Integer fileNos[] = new Integer[3];
		fileNos[0] = 1;
		fileNos[1] = 2;
		fileNos[2] = 3;
		
		writePost();

		postService.write(post1, fileNos);
		postService.write(post2, fileNos);
		postService.write(post3, fileNos);
	}
	
	@After
	public void deleteAll() throws Exception {
		postService.deleteAll();
	}
   
	@Test
	public void testPostService() throws Exception {

		assertThat(postService.getCount("N"), is(3));
		
		Post post4 = postService.get(post1.getPostNo(), "N");
		Post post5 = postService.get(post2.getPostNo(), "N");
		Post post6 = postService.get(post3.getPostNo(), "N");
		
		assertThat(post1.toString(), is(post4.toString()));
		assertThat(post2.toString(), is(post5.toString()));
		assertThat(post3.toString(), is(post6.toString()));
		
		post5 = new Post();
		post5.setPostNo(post2.getPostNo());
		post5.setPostAuthor("kurien5555");
		post5.setPostSubject("수정!수정!!!");
		post5.setPostReservationTime(new Timestamp(0));
		post5.setPostWriteTime(new Timestamp(0));
		post5.setPostContent("수정!!!!");

		Integer fileNos[] = null;
		postService.modify(post5, fileNos);

		System.out.println(post2);
		System.out.println(post5);
		
		List<Post> posts = postService.getList("N");
		
		List<Post> posts2 = new ArrayList<Post>();
		
		
//		post2.setPostAuthor("kurien5555"); author은 수정 불가능
		post2.setPostSubject("수정!수정!!!");
//		post2.setPostReservationTime(new Timestamp(0)); 예약시간은 0 eq null임. 
		post2.setPostReservationTime(null);
//		post2.setPostWriteTime(new Timestamp(0)); write time은 수정 불가능
		post2.setPostContent("수정!!!!");
		
		posts2.add(post3);
		posts2.add(post2);
		posts2.add(post1);
		
		assertThat(posts.toString(), is(posts2.toString()));
		
		assertThat(postService.getCount("N"), is(3));
		
		List<Integer> postNos = new ArrayList<Integer>();
		
		postNos.add(post2.getPostNo());
		postNos.add(post3.getPostNo());
		
		postService.deleteList(postNos);
		
		assertThat(postService.getCount("N"), is(1));

		assertThat(postService.get(post1.getPostNo(), "N").toString(), is(post1.toString()));
	}
	
	@Test
	public void modifyNotUsePrimaryKeyExceptionTest() throws Exception {
		exception.expect(NotUsePrimaryKeyException.class);
		exception.expectMessage("수정될 포스트의 번호가 입력되지 않았습니다.");
		
		Post post4 = new Post();
		post4.setPostAuthor("kurien4444444444");
		post4.setPostSubject("수정!수정!");
		post4.setPostReservationTime(new Timestamp(0));
		post4.setPostWriteTime(new Timestamp(0));
		post4.setPostContent("수정!");

		postService.modify(post4, null);
	}
	
	@Test
	public void modifyNotFoundDataExceptionTest() throws Exception {
		Post post5 = new Post();
		post5.setPostNo(500);
		post5.setPostAuthor("kurien4444444444");
		post5.setPostSubject("수정!수정!");
		post5.setPostReservationTime(new Timestamp(0));
		post5.setPostWriteTime(new Timestamp(0));
		post5.setPostContent("수정!");

		exception.expect(NotFoundDataException.class);
		exception.expectMessage("500번 포스트를 찾을 수 없습니다.");

		postService.modify(post5, null);
	}
	
	@Test
	public void writeDuplicationKeyExceptionTest() throws Exception {
		exception.expect(DuplicatedKeyException.class);
		exception.expectMessage("1번 포스트가 이미 존재합니다.");
		
		postService.write(post1, null);
	}
	
	@Test
	public void deleteListEmptyParameterExceptionTest() throws Exception {
		exception.expect(EmptyParameterException.class);
		exception.expectMessage("빈 데이터가 전달되었습니다.");
		postService.deleteList(new ArrayList<Integer>());
	}
	
	public void writePost() {
		Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
		today.setNanos(0);
		
		post1 = new Post();
		
		post1.setPostNo(null);
		post1.setPostAuthor("kurien1");
		post1.setPostPassword("test1");
		post1.setPostSubject("블로그 제목111입니다.");
		post1.setPostContent("블로그 글입111니다.");
		post1.setPostReservationTime(null);
		post1.setPostView(PostViewStatus.TRUE);
		post1.setPostPublish(PostPublishStatus.FALSE);
		post1.setPostWriteIp("192.168.0.1");
		post1.setPostWriteTime(today);
		
		post2 = new Post();
		
		post2.setPostNo(null);
		post2.setPostAuthor("kurien2");
		post2.setPostPassword("tes22t");
		post2.setPostSubject("블로그 제22목2입니다.");
		post2.setPostContent("블2로그 글2입니다.");
		post2.setPostReservationTime(null);
		post2.setPostView(PostViewStatus.TRUE);
		post2.setPostPublish(PostPublishStatus.FALSE);
		post2.setPostWriteIp("192.168.0.1");
		post2.setPostWriteTime(today);
		
		post3 = new Post();
		
		post3.setPostNo(null);
		post3.setPostAuthor("kurien3");
		post3.setPostPassword("te33t");
		post3.setPostSubject("블로그3 제목입3니다.");
		post3.setPostContent("블로그 333글입니다.");
		post3.setPostReservationTime(null);
		post3.setPostView(PostViewStatus.TRUE);
		post3.setPostPublish(PostPublishStatus.FALSE);
		post3.setPostWriteIp("192.168.0.1");
		post3.setPostWriteTime(today);
	}
}