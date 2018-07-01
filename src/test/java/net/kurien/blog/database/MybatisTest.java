package net.kurien.blog.database;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.kurien.blog.database.dao.MybatisTestDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"})
@WebAppConfiguration
public class MybatisTest {
	@Resource
    private MybatisTestDao mybatisTestDao;

    @Test
    public void mybatisTest() throws Exception{
    	System.out.println(mybatisTestDao.selectList("한글"));
    }
}
