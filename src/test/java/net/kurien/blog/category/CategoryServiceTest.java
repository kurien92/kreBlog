package net.kurien.blog.category;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.kurien.blog.module.category.dao.impl.CategoryDaoBasic;
import net.kurien.blog.module.category.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class CategoryServiceTest {
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private CategoryDaoBasic categoryDao;
	
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	
	@Before
	public void setup() throws Exception {
	}
   
	@Test
	public void testGetCategoryHTML() throws Exception {
		String contextPath = "/";
		String html = categoryService.getCategoryHTML(contextPath);
		
		System.out.println(html);
	}
}