package net.kurien.blog.module.file.service.impl;

import junit.framework.TestCase;
import net.kurien.blog.module.file.service.ServiceFileService;
import static org.hamcrest.CoreMatchers.is;
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
public class BasicServiceFileServiceTest extends TestCase {
    @Inject
    private ServiceFileService serviceFileService;

    @Test
    public void getCount() {
        assertThat(serviceFileService.getCount(84), is(1));
    }

//    @Test
//    public void testGetCount() {
//        assertThat(serviceFileService.getCount("post", 20), is(1));
//    }
}