package net.kurien.blog.module.file.dao.impl;

import junit.framework.TestCase;
import net.kurien.blog.module.file.dao.ServiceFileDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class BasicServiceFileDaoTest extends TestCase {
    @Inject
    private ServiceFileDao serviceFileDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectOne() {
        System.out.println(serviceFileDao.selectOne("post", 21, 81));
    }

    @Test
    public void delete() {
        serviceFileDao.delete(85);
    }

}