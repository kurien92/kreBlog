package net.kurien.blog.module.content.service.impl;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.DuplicatedKeyException;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.content.entity.Content;
import net.kurien.blog.module.content.entity.ContentViewStatus;
import net.kurien.blog.module.content.service.ContentService;
import net.kurien.blog.util.TimeUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class BasicContentServiceTest {
    @Inject
    private ContentService contentService;

    private Content content1;

    @Before
    public void setUp() throws Exception {
        content1 = new Content();

        content1.setContentId("contentTest1");
        content1.setContentTitle("컨텐츠 제목1");
        content1.setContent("컨텐츠 내용1");
        content1.setContentView(ContentViewStatus.TRUE);

        contentService.deleteAll();

        assertThat(contentService.getCount("N"), is(0));


        contentService.create(content1, new Integer[]{});

        assertThat(contentService.getCount("N"), is(1));
    }

    @Test
    public void create() throws DuplicatedKeyException {
        Content content = new Content();

        content.setContentId("contentTest2");
        content.setContentTitle("컨텐츠 제목2");
        content.setContent("컨텐츠 내용2");
        content.setContentView(ContentViewStatus.TRUE);

        contentService.create(content, new Integer[]{});
        assertThat(contentService.getCount("N"), is(2));
    }

    @Test
    public void update() throws NotFoundDataException {
        Content content = new Content();

        content.setContentId(content1.getContentId());
        content.setContentTitle("updated Content Title");
        content.setContent("updated Content");
        content.setContentView(ContentViewStatus.TRUE);

        contentService.update(content, new Integer[]{});

        Content content2 = contentService.get(content1.getContentId(), "Y");

        assertThat(content2.getContentId(), is(content1.getContentId()));
        assertThat(content2.getContentTitle(), is(content.getContentTitle()));
        assertThat(content2.getContent(), is(content.getContent()));
        assertThat(content2.getContentView(), is(content.getContentView()));
        assertThat(content2.getContentWriteTime(), is(content.getContentWriteTime()));
    }

    @Test
    public void delete() {
        contentService.delete("contentTest1");
    }

    @Test
    public void get() throws NotFoundDataException {
        Content content = contentService.get(content1.getContentId(), "N");

        assertThat(content.toString(), is(content1.toString()));
    }

    @Test
    public void list() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPage(1);
        searchCriteria.setRowCount(20);

        List<Content> contents = contentService.getList("N", searchCriteria);

        assertThat(contents.get(0).toString(), is(content1.toString()));
    }

    @Test
    public void getCount() {
        assertThat(contentService.getCount("N"), is(1));
    }

    @Test
    public void deleteAll() {
        contentService.deleteAll();

        assertThat(contentService.getCount("N"), is(0));
    }
}