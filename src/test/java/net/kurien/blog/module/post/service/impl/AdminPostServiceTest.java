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

import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.entity.PostPublishStatus;
import net.kurien.blog.module.post.entity.PostViewStatus;
import net.kurien.blog.module.post.service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class AdminPostServiceTest {
	@Inject
	private PostService postService;
	
	@Inject
	private ServiceFileService serviceFileService;
	
	private Post hidePost = null;
	private Post showPost = null;
	private Post reservePastPost = null;
	private Post reserveFuturePost = null;
	private Post reserveWithoutReserveTimePost = null;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	@Before
	public void setup() throws Exception {
		postService.deleteAll();
		
		assertThat(postService.getCount("N"), is(0));

		Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
		today.setNanos(0);

		hidePost = new Post();
		
		hidePost.setPostNo(null);
		hidePost.setCategoryId("test");
		hidePost.setPostAuthor("kurien");
		hidePost.setPostPassword("");
		hidePost.setPostSubject("비공개 글입니다.");
		hidePost.setPostContent("비공개 글입니다.");
		hidePost.setPostReservationTime(null);
		hidePost.setPostView(PostViewStatus.FALSE);
		hidePost.setPostPublish(PostPublishStatus.FALSE);
		hidePost.setPostWriteIp("106.249.238.106");
		hidePost.setPostWriteTime(today);
		
		showPost = new Post();
		
		showPost.setPostNo(null);
		showPost.setCategoryId("test");
		showPost.setPostAuthor("kurien");
		showPost.setPostPassword("");
		showPost.setPostSubject("공개 글입니다.");
		showPost.setPostContent("공개 글입니다.");
		showPost.setPostReservationTime(null);
		showPost.setPostView(PostViewStatus.TRUE);
		showPost.setPostPublish(PostPublishStatus.FALSE);
		showPost.setPostWriteIp("106.249.238.106");
		showPost.setPostWriteTime(today);


		Calendar reservationCalendar = Calendar.getInstance();
		reservationCalendar.set(2000, 12, 31);
		
		Timestamp reservationTime = new Timestamp(reservationCalendar.getTimeInMillis());
		reservationTime.setNanos(0);
		
		reservePastPost = new Post();
		
		reservePastPost.setPostNo(null);
		reservePastPost.setCategoryId("test");
		reservePastPost.setPostAuthor("kurien");
		reservePastPost.setPostPassword("");
		reservePastPost.setPostSubject("공개 발행예약 글입니다.");
		reservePastPost.setPostContent("공개 발행예약 글입니다.");
		reservePastPost.setPostReservationTime(reservationTime);
		reservePastPost.setPostView(PostViewStatus.TRUE);
		reservePastPost.setPostPublish(PostPublishStatus.TRUE);
		reservePastPost.setPostWriteIp("106.249.238.106");
		reservePastPost.setPostWriteTime(today);
		
		
		reservationCalendar = Calendar.getInstance();
		reservationCalendar.set(9000, 12, 31);
		
		reservationTime = new Timestamp(reservationCalendar.getTimeInMillis());
		reservationTime.setNanos(0);
		
		
		reserveFuturePost = new Post();
		
		reserveFuturePost.setPostNo(null);
		reserveFuturePost.setCategoryId("test");
		reserveFuturePost.setPostAuthor("kurien");
		reserveFuturePost.setPostPassword("");
		reserveFuturePost.setPostSubject("공개 발행예약 글입니다.");
		reserveFuturePost.setPostContent("공개 발행예약 글입니다.");
		reserveFuturePost.setPostReservationTime(reservationTime);
		reserveFuturePost.setPostView(PostViewStatus.TRUE);
		reserveFuturePost.setPostPublish(PostPublishStatus.TRUE);
		reserveFuturePost.setPostWriteIp("106.249.238.106");
		reserveFuturePost.setPostWriteTime(today);
		
		reserveWithoutReserveTimePost = new Post();
		
		reserveWithoutReserveTimePost.setPostNo(null);
		reserveWithoutReserveTimePost.setCategoryId("test");
		reserveWithoutReserveTimePost.setPostAuthor("kurien");
		reserveWithoutReserveTimePost.setPostPassword("");
		reserveWithoutReserveTimePost.setPostSubject("공개 발행예약 글이지만 예약시간이 없는 경우입니다.");
		reserveWithoutReserveTimePost.setPostContent("공개 발행예약 글이지만 예약시간이 없는 경우입니다.");
		reserveWithoutReserveTimePost.setPostReservationTime(null);
		reserveWithoutReserveTimePost.setPostView(PostViewStatus.TRUE);
		reserveWithoutReserveTimePost.setPostPublish(PostPublishStatus.TRUE);
		reserveWithoutReserveTimePost.setPostWriteIp("106.249.238.106");
		reserveWithoutReserveTimePost.setPostWriteTime(today);

		serviceFileService.removeAll();
		
		Integer fileNos[] = new Integer[3];
		fileNos[0] = 1;
		fileNos[1] = 2;
		fileNos[2] = 3;
		
		postService.write(hidePost, fileNos);
		postService.write(showPost, fileNos);
		postService.write(reservePastPost, fileNos);
		postService.write(reserveFuturePost, fileNos);
		postService.write(reserveWithoutReserveTimePost, fileNos);
	}
	
	@After
	public void deleteAll() throws Exception {
		postService.deleteAll();
	}
	
	/**
	 * 관리가능여부("Y")에 따라 Post가 보여질지 안보여질지를 결정한다.
	 * 
	 * 1. 관리자인 경우
	 * 1.1. 글 공개여부와 상관없이 Post가 보여진다.
	 * 1.2. 예약시간에 관계 없이 Post가 보여진다.
	 * @throws Exception 
	 */
	@Test
	public void adminShowPost() throws Exception {
		assertThat(postService.getCount("Y"), is(5));
		
		assertThat(postService.get(hidePost.getPostNo(), "Y").toString(), is(hidePost.toString()));
		assertThat(postService.get(showPost.getPostNo(), "Y").toString(), is(showPost.toString()));
		assertThat(postService.get(reservePastPost.getPostNo(), "Y").toString(), is(reservePastPost.toString()));
		assertThat(postService.get(reserveFuturePost.getPostNo(), "Y").toString(), is(reserveFuturePost.toString()));
		assertThat(postService.get(reserveWithoutReserveTimePost.getPostNo(), "Y").toString(), is(reserveWithoutReserveTimePost.toString()));

		List<String> categoryIds = new ArrayList<>();
		categoryIds.add("test");
		
		assertThat(postService.getListByCategoryIds(categoryIds, "Y", null).size(), is(5));
		assertThat(postService.getCountByCategoryIds(categoryIds, "Y", null), is(5));
	}
	/**
	 * 관리가능여부("N")에 따라 Post가 보여질지 안보여질지를 결정한다.
	 * 
	 * 2. 관리자가 아닌 경우
	 * 2.1. 글 공개여부가 비공개라면 Post가 보이지 않는다.
	 * 2.2. 글 공개여부가 공개이고 예약발행이 0이라면 Post가 보여진다.
	 * 2.3. 글 공개여부가 예약발행이 1이라면 Post가 예약시간 이후에 보여진다.
	 * @throws Exception 
	 */
	@Test
	public void userShowPost() throws Exception {
		assertThat(postService.getCount("N"), is(2));
		
		assertThat(postService.get(showPost.getPostNo(), "N").toString(), is(showPost.toString()));
		assertThat(postService.get(reservePastPost.getPostNo(), "N").toString(), is(reservePastPost.toString()));
		
		List<String> categoryIds = new ArrayList<>();
		categoryIds.add("test");
		
		assertThat(postService.getListByCategoryIds(categoryIds, "N", null).size(), is(2));
		assertThat(postService.getCountByCategoryIds(categoryIds, "N", null), is(2));
	}
	
	@Test
	public void userReserveFuturePost() throws Exception {
		exception.expect(NotFoundDataException.class);
		exception.expectMessage(reserveFuturePost.getPostNo() + "번 포스트를 찾을 수 없습니다.");
		assertThat(postService.get(reserveFuturePost.getPostNo(), "N").toString(), is(reserveFuturePost.toString()));
	}
	
	@Test
	public void userHidePost() throws Exception {
		exception.expect(NotFoundDataException.class);
		exception.expectMessage(hidePost.getPostNo() + "번 포스트를 찾을 수 없습니다.");
		assertThat(postService.get(hidePost.getPostNo(), "N").toString(), is(hidePost.toString()));
	}
	
	/**
	 * 예외. 예약발행이지만 오류로 인해 예약시간이 들어가지 않은 경우 
	 * @throws Exception
	 */
	@Test
	public void userReserveWithoutReserveTimePost() throws Exception {
		exception.expect(NotFoundDataException.class);
		exception.expectMessage(reserveWithoutReserveTimePost.getPostNo() + "번 포스트를 찾을 수 없습니다.");
		assertThat(postService.get(reserveWithoutReserveTimePost.getPostNo(), "N").toString(), is(reserveWithoutReserveTimePost.toString()));
	}
}