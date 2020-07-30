package net.kurien.blog.module.autosave.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.kurien.blog.module.autosave.entity.Autosave;
import net.kurien.blog.util.TimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class AutosaveServiceTest {
    private Autosave autosave;

    @Inject
    private AutosaveService autosaveService;

    @Before
    public void setUp() throws Exception {
        autosaveService.removeAll();

        assertThat(autosaveService.count(), is(0));

        JsonObject jsonObject = new JsonObject();

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("test1");
        jsonArray.add("test2");
        jsonArray.add("test3");

        jsonObject.addProperty("test", "save");
        jsonObject.add("test", jsonArray);

        autosave = new Autosave();

        autosave.setAsSaveTime(TimeUtil.currentTime());
        autosave.setAsJsonData(jsonObject.toString());

        autosaveService.save(autosave);

        assertThat(autosaveService.count(), is(1));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void get() {
        Autosave autosave2 = autosaveService.get(1l);

        assertThat(autosave2, is(autosave));
    }

    @Test
    public void getList() {
        List<Long> asNos = new ArrayList<>();
        asNos.add(autosave.getAsNo());

        List<Autosave> autosaves = autosaveService.getList(asNos);

        assertThat(autosaves.get(0), is(autosave));
    }

    @Test
    public void save() {
        JsonObject jsonObject = new JsonObject();

        JsonArray jsonArray = new JsonArray();
        jsonArray.add("test1");
        jsonArray.add("test2");
        jsonArray.add("test3");

        jsonObject.addProperty("test", "save");
        jsonObject.add("test", jsonArray);

        Autosave autosave2 = new Autosave();

        autosave2.setAsSaveTime(TimeUtil.currentTime());
        autosave2.setAsJsonData(jsonObject.toString());

        autosaveService.save(autosave2);

        assertThat(autosaveService.count(), is(2));
    }

    @Test
    public void remove() {
        autosaveService.remove(1l);

        assertThat(autosaveService.count(), is(0));
    }

    @Test
    public void count() {
        int count = autosaveService.count();

        assertThat(count, is(1));
    }

    @Test
    public void isExist() {
        assertThat(autosaveService.isExist(autosave.getAsNo()), is(true));
    }

    @Test
    public void removeAll() {
        autosaveService.removeAll();

        assertThat(autosaveService.count(), is(0));
    }
}