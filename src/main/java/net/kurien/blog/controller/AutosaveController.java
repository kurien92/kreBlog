package net.kurien.blog.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.kurien.blog.module.autosave.entity.Autosave;
import net.kurien.blog.module.autosave.entity.ServiceAutosave;
import net.kurien.blog.module.autosave.service.AutosaveService;
import net.kurien.blog.module.autosave.service.ServiceAutosaveService;
import net.kurien.blog.util.RequestUtil;
import net.kurien.blog.util.TimeUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/autosave")
public class AutosaveController {
    @Inject
    private AutosaveService autosaveService;

    @Inject
    private ServiceAutosaveService serviceAutosaveService;

    @RequestMapping("/save/{serviceName}")
    public JsonObject save(@PathVariable String serviceName, String jsonData, Authentication authentication, HttpServletRequest request) {
        User user = (User) authentication.getPrincipal();

        Autosave autosave = new Autosave();
        autosave.setAsJsonData(jsonData);

        autosaveService.save(autosave);

        ServiceAutosave serviceAutosave = new ServiceAutosave();
        serviceAutosave.setServiceName(serviceName);
        serviceAutosave.setAsNo(autosave.getAsNo());
        serviceAutosave.setServiceAsUsername(user.getUsername());
        serviceAutosave.setServiceAsWriteIp(RequestUtil.getRemoteAddr(request));
        serviceAutosave.setServiceAsExpireTime(TimeUtil.addTime(60 * 60 * 24 * 30));

        serviceAutosaveService.add(serviceAutosave);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        JsonObject json = new JsonObject();
        JsonObject autosaveJson = new JsonObject();
        autosaveJson.addProperty("no", autosave.getAsNo());
        autosaveJson.addProperty("jsonData", autosave.getAsJsonData());
        autosaveJson.addProperty("time", sdf.format(autosave.getAsSaveTime()));
        autosaveJson.addProperty("expireTime", sdf.format(serviceAutosave.getServiceAsExpireTime()));

        json.addProperty("result", "success");
        json.add("value", autosaveJson);
        json.addProperty("message", "");

        return json;
    }


    @RequestMapping("/list/{serviceName}")
    public JsonObject list(@PathVariable String serviceName, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        List<ServiceAutosave> serviceAutosaves = serviceAutosaveService.getList(serviceName, user.getUsername());

        JsonObject json = new JsonObject();
        JsonArray autosaveJsonArray = new JsonArray();

        if(serviceAutosaves.size() == 0) {
            json.addProperty("result", "success");
            json.add("value", autosaveJsonArray);
            json.addProperty("message", "");

            return json;
        }

        List<Long> asNos = new ArrayList<>();

        for(int i = 0; i < serviceAutosaves.size(); i++) {
            asNos.add(serviceAutosaves.get(i).getAsNo());
        }

        // 추후에 Join으로 한번에 처리
        List<Autosave> autosaves = autosaveService.getList(asNos);

        Map<Long, ServiceAutosave> serviceAutosaveMap = new HashMap<>();

        for(int i = 0; i < serviceAutosaves.size(); i++) {
            serviceAutosaveMap.put(serviceAutosaves.get(i).getAsNo(), serviceAutosaves.get(i));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for(Autosave autosave : autosaves) {
            JsonObject autosaveJson = new JsonObject();

            Date expireTime = serviceAutosaveMap.get(autosave.getAsNo()).getServiceAsExpireTime();

            autosaveJson.addProperty("no", autosave.getAsNo());
            autosaveJson.addProperty("jsonData", autosave.getAsJsonData());
            autosaveJson.addProperty("time", sdf.format(autosave.getAsSaveTime()));
            autosaveJson.addProperty("expireTime", sdf.format(expireTime));

            autosaveJsonArray.add(autosaveJson);
        }

        json.addProperty("result", "success");
        json.add("value", autosaveJsonArray);
        json.addProperty("message", "");

        return json;
    }

    @RequestMapping("/remove/{serviceName}/{asNo}")
    public JsonObject save(@PathVariable String serviceName, @PathVariable Long asNo, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        JsonObject json = new JsonObject();

        ServiceAutosave serviceAutosave = serviceAutosaveService.get(serviceName, asNo);

        if(serviceAutosave == null) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", "이미 삭제된 자료입니다.");

            return json;
        }

        if(serviceAutosave.getServiceAsUsername().equals(user.getUsername()) == false) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", "삭제 권한이 없는 자료입니다.");

            return json;
        }


        if(autosaveService.isExist(asNo) == false) {
            json.addProperty("result", "fail");
            json.add("value", new JsonObject());
            json.addProperty("message", "이미 삭제된 자료입니다.");

            return json;
        }

        serviceAutosaveService.remove(asNo);
        autosaveService.remove(asNo);

        json.addProperty("result", "success");
        json.add("value", new JsonObject());
        json.addProperty("message", "");

        return json;
    }
}
