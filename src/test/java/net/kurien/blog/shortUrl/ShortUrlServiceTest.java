package net.kurien.blog.shortUrl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.kurien.blog.module.shortUrl.service.ShortUrlService;
import net.kurien.blog.util.Base62Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class ShortUrlServiceTest {
//	@Inject
//	private ShortUrlService shortUrlService;
	
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	
	@Before
	public void setup() throws Exception {
	}
   
	@Test
	public void util() throws Exception {
		long test = 2389471982357192l;
		String encoded = Base62Util.encode(test);
		long decoded = Base62Util.decode(encoded);
		
		assertThat("GO6WN9F6k", is(encoded));
		assertThat(Long.toString(test), is(Long.toString(decoded)));
	}
}