package net.kurien.blog.controller;

import com.google.gson.JsonObject;
import net.kurien.blog.module.autosave.entity.Autosave;
import net.kurien.blog.module.autosave.entity.ServiceAutosave;
import net.kurien.blog.module.autosave.service.AutosaveService;
import net.kurien.blog.module.autosave.service.ServiceAutosaveService;
import net.kurien.blog.util.RequestUtil;
import net.kurien.blog.util.TimeUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/autosave")
public class AutosaveController {
    @Inject
    private AutosaveService autosaveService;

    @Inject
    private ServiceAutosaveService serviceAutosaveService;

    @RequestMapping("/save/{serviceName}/{serviceNo}")
    public JsonObject save(@PathVariable String serviceName, @PathVariable long serviceNo, JsonObject jsonData, HttpServletRequest request) {
        Autosave autosave = new Autosave();
        autosave.setAsJsonData(jsonData.toString());

        autosaveService.save(autosave);

        ServiceAutosave serviceAutosave = new ServiceAutosave();
        serviceAutosave.setServiceName(serviceName);
        serviceAutosave.setServiceNo(serviceNo);
        serviceAutosave.setAsNo(autosave.getAsNo());
        serviceAutosave.setServiceAsUsername("asd");
        serviceAutosave.setServiceAsWriteIp(RequestUtil.getRemoteAddr(request));
        serviceAutosave.setServiceAsExpireTime(TimeUtil.addTime(60 * 60 * 24 * 30));

        serviceAutosaveService.add(serviceAutosave);

        return new JsonObject();
    }
}
