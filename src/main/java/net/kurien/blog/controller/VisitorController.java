package net.kurien.blog.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;

import net.kurien.blog.module.visitor.entity.Visitor;
import net.kurien.blog.module.visitor.service.VisitorService;
import net.kurien.blog.util.RequestUtil;
import net.kurien.blog.util.TokenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visitor")
public class VisitorController {
	private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

	private final VisitorService visitorService;

	@Autowired
	public VisitorController(VisitorService visitorService) {
		this.visitorService = visitorService;
	}

	@RequestMapping(value = "/collect", method = RequestMethod.POST)
	public JsonObject list(Visitor visitor, HttpServletRequest request) {
		visitor.setVisitorIp(RequestUtil.getRemoteAddr(request));
		visitorService.collect(visitor);

		JsonObject json = new JsonObject();
	    json.addProperty("result", "success");
	    json.add("value", new JsonObject());
	    json.addProperty("message", "");
		
		return json;
	}

	@RequestMapping(value = "/getUserCookieToken", method = RequestMethod.GET)
	public JsonObject getUserCookieToken() {
		JsonObject json = new JsonObject();
		
		JsonObject jsonValue = new JsonObject();
		
		try {
			jsonValue.addProperty("token", TokenUtil.getTokenString());
		} catch (NoSuchAlgorithmException nsae) {
			// TODO Auto-generated catch block
		    json.addProperty("result", "fail");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "알 수 없는 오류가 발생했습니다.");
		    return json;
		}

	    json.addProperty("result", "success");
	    json.add("value", jsonValue);
	    json.addProperty("message", "");
		
		return json;
	}
}
