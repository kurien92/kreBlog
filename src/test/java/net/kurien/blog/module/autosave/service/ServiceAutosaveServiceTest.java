package net.kurien.blog.module.autosave.service;

import net.kurien.blog.module.autosave.entity.ServiceAutosave;
import net.kurien.blog.util.TimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class ServiceAutosaveServiceTest {
    private ServiceAutosave serviceAutosave;

    @Inject
    private ServiceAutosaveService serviceAutosaveService;

    @Before
    public void setUp() throws Exception {
        serviceAutosaveService.removeAll();

        assertThat(serviceAutosaveService.count(), is(0));

        serviceAutosave = new ServiceAutosave();

        serviceAutosave.setServiceName("test");
        serviceAutosave.setAsNo(1l);
        serviceAutosave.setServiceAsUsername("kurien");
        serviceAutosave.setServiceAsExpireTime(TimeUtil.addTime(180));
        serviceAutosave.setServiceAsWriteTime(TimeUtil.currentTime());
        serviceAutosave.setServiceAsWriteIp("192.168.0.1");

        serviceAutosaveService.add(serviceAutosave);

        assertThat(serviceAutosaveService.count(), is(1));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        ServiceAutosave serviceAutosave2 = new ServiceAutosave();

        serviceAutosave2.setServiceName("test");
        serviceAutosave2.setAsNo(2l);
        serviceAutosave2.setServiceAsUsername("kurien");
        serviceAutosave2.setServiceAsExpireTime(TimeUtil.addTime(180));
        serviceAutosave2.setServiceAsWriteTime(TimeUtil.currentTime());
        serviceAutosave2.setServiceAsWriteIp("192.168.0.1");

        serviceAutosaveService.add(serviceAutosave2);

        assertThat(serviceAutosaveService.count(), is(2));
    }

    @Test
    public void getList() {
        List<ServiceAutosave> serviceAutosave2 = serviceAutosaveService.getList("test", "kurien");

        assertThat(serviceAutosave2.get(0), is(serviceAutosave));
    }

    @Test
    public void testGet() {
        List<ServiceAutosave> serviceAutosave2 = serviceAutosaveService.getList(1l);

        assertThat(serviceAutosave2.get(0), is(serviceAutosave));
    }

    @Test
    public void remove() {
        serviceAutosaveService.remove("test", "kurien");
        assertThat(serviceAutosaveService.count(), is(0));
    }

    @Test
    public void testRemove() {
        serviceAutosaveService.remove(1l);
        assertThat(serviceAutosaveService.count(), is(0));
    }

    @Test
    public void count() {
        int count = serviceAutosaveService.count();

        assertThat(count, is(1));
    }

    @Test
    public void removeAll() {
        serviceAutosaveService.removeAll();

        assertThat(serviceAutosaveService.count(), is(0));
    }
}