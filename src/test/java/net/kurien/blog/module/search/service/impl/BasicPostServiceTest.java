package net.kurien.blog.module.search.service.impl;

import junit.framework.TestCase;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.post.entity.PostPublishStatus;
import net.kurien.blog.module.post.entity.PostViewStatus;
import net.kurien.blog.module.post.service.PostService;
import net.kurien.blog.module.search.dto.SearchDTO;
import net.kurien.blog.module.search.dto.Searchable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class BasicPostServiceTest extends TestCase {
    @Inject
    private PostService postService;

    @Inject
    private ServiceFileService serviceFileService;

    private Post post1 = null;
    private Post post2 = null;
    private Post post3 = null;

    @Before
    public void setUp() throws Exception {
        postService.deleteAll();
        serviceFileService.removeAll();

        assertThat(postService.getCount("N"), is(0));

        Integer fileNos[] = new Integer[3];
        fileNos[0] = 1;
        fileNos[1] = 2;
        fileNos[2] = 3;

        Timestamp today = new Timestamp(Calendar.getInstance().getTimeInMillis());
        today.setNanos(0);

        post1 = new Post();

        post1.setPostNo(null);
        post1.setPostAuthor("kurien");
        post1.setPostPassword("test");
        post1.setPostSubject("블로그 제목 1번입니다.");
        post1.setPostContent("블로그 글 1번입니다.");
        post1.setPostReservationTime(null);
        post1.setPostView(PostViewStatus.TRUE);
        post1.setPostPublish(PostPublishStatus.FALSE);
        post1.setPostWriteIp("192.168.0.1");
        post1.setPostWriteTime(today);

        post2 = new Post();

        post2.setPostNo(null);
        post2.setPostAuthor("kurien");
        post2.setPostPassword("test");
        post2.setPostSubject("블로그 제목 2번! 입니다.");
        post2.setPostContent("블로그 글 2번입니다.");
        post2.setPostReservationTime(null);
        post2.setPostView(PostViewStatus.TRUE);
        post2.setPostPublish(PostPublishStatus.FALSE);
        post2.setPostWriteIp("192.168.0.1");
        post2.setPostWriteTime(today);

        post3 = new Post();

        post3.setPostNo(null);
        post3.setPostAuthor("kurien");
        post3.setPostPassword("test");
        post3.setPostSubject("블로그 제목 3번! 입니다.");
        post3.setPostContent("블로그 글 3번입니다.");
        post3.setPostReservationTime(null);
        post3.setPostView(PostViewStatus.TRUE);
        post3.setPostPublish(PostPublishStatus.FALSE);
        post3.setPostWriteIp("192.168.0.1");
        post3.setPostWriteTime(today);

        postService.write(post1, fileNos);
        postService.write(post2, fileNos);
        postService.write(post3, fileNos);
    }

    /**
     * 띄어쓰기를 기준으로 문자열을 나누어 검색한다.
     */
    @Test
    public void search() {
        String keyword = "블로그 번! 제목";
        String[] keywords = keyword.split(" ");

        assertThat(((Searchable)postService).search(keywords).getContents().size(), is(2));

        keyword = "2번 입니다.";
        keywords = keyword.split(" ");

        assertThat(((Searchable)postService).search(keywords).getContents().size(), is(1));

        keyword = "블 로 그 제 목 번 입 니 다 .";
        keywords = keyword.split(" ");

        assertThat(((Searchable)postService).search(keywords).getContents().size(), is(3));


        keyword = "블로그           1 1 1 1 1      1번";
        keywords = keyword.split(" ");

        assertThat(((Searchable)postService).search(keywords).getContents().size(), is(1));
    }
}