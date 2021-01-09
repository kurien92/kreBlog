package net.kurien.blog.module.content.dao.impl;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.content.dao.ContentDao;
import net.kurien.blog.module.content.entity.Content;
import net.kurien.blog.module.content.entity.ContentViewStatus;
import net.kurien.blog.util.TimeUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class BasicContentDaoTest {
    @Inject
    private ContentDao contentDao;

    private Content content1;

    @Before
    public void setUp() throws Exception {
        content1 = new Content();

        content1.setContentId("contentDaoTest1");
        content1.setContentTitle("contentDaoTitle1");
        content1.setContent("contentDaoContent1");
        content1.setContentWriteTime(TimeUtil.currentTime());
        content1.setContentView(ContentViewStatus.TRUE);

        contentDao.deleteAll();

        assertThat(contentDao.selectCount("N"), is(0));

        contentDao.insert(content1);

        assertThat(contentDao.selectCount("N"), is(1));
    }

    @Test
    public void insert() {
        Content content = new Content();

        content.setContentId("contentDaoTest2");
        content.setContentTitle("contentDaoTitle2");
        content.setContent("contentDaoContent2");
        content.setContentWriteTime(TimeUtil.currentTime());
        content.setContentView(ContentViewStatus.TRUE);

        contentDao.insert(content);

        assertThat(contentDao.selectCount("N"), is(2));
    }

    @Test
    public void update() {
        Content content = new Content();

        content.setContentNo(content1.getContentNo());
        content.setContentId(content1.getContentId());
        content.setContentTitle("contentDaoTitle");
        content.setContent("contentDaoContent");
        content.setContentView(ContentViewStatus.FALSE);
        content.setContentWriteTime(TimeUtil.addTime(100000));

        contentDao.update(content);

        Content content2 = contentDao.selectOne(content1.getContentId(), "Y");

        assertThat(content.toString(), is(content2.toString()));
    }

    @Test
    public void delete() {
        contentDao.delete(content1.getContentId());
    }

    @Test
    public void selectOne() {
        Content content = contentDao.selectOne(content1.getContentId(), "N");

        assertThat(content.toString(), is(content1.toString()));
    }

    @Test
    public void selectList() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPage(1);
        searchCriteria.setRowCount(20);

        List<Content> content = contentDao.selectList("N", searchCriteria);

        assertThat(content.get(0).toString(), is(content1.toString()));
    }

    @Test
    public void selectCount() {
        assertThat(contentDao.selectCount("N"), is(1));
    }

    @Test
    public void deleteAll() {
        contentDao.deleteAll();

        assertThat(contentDao.selectCount("N"), is(0));
    }
}