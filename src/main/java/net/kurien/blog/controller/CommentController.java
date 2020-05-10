package net.kurien.blog.controller;

import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@RequestMapping("/reply/{commentNo}")
	public @ResponseBody JsonObject reply(@PathVariable String commentNo, Model model) throws Exception {
	    JsonObject json = new JsonObject();
	    
        JsonObject commentData = new JsonObject();
	    
	    commentData.addProperty("no", 2);
	    commentData.addProperty("name", "kurien");
	    commentData.addProperty("writeTime", "2020-05-11 12:43");
	    commentData.addProperty("text", "댓글입니다.");
        
	    json.addProperty("result", "success");
	    json.add("value", commentData);
	    json.addProperty("message", "");
        
	    return json;
    }
    
	@RequestMapping("/modify/{commentNo}")
	public JsonObject modify(@PathVariable String commentNo, Model model) throws Exception {
	    JsonObject json = new JsonObject();
	    
        JsonObject commentData = new JsonObject();
	    
	    commentData.addProperty("no", 2);
	    commentData.addProperty("name", "kurien");
	    commentData.addProperty("writeTime", "2020-05-11 12:43");
	    commentData.addProperty("text", "댓글입니다.");
        
	    json.addProperty("result", "success");
	    json.add("value", commentData);
	    json.addProperty("message", "");
        
	    return json;
    }
    
	@RequestMapping("/delete/{commentNo}")
	public JsonObject delete(@PathVariable String commentNo, Model model) throws Exception {
	    JsonObject json = new JsonObject();
	    
	    json.addProperty("result", "success");
	    json.addProperty("value", "");
	    json.addProperty("message", "");
        
	    return json;
    }
    
	@RequestMapping("/passwordCheck/{commentNo}")
	public JsonObject passwordCheck(@PathVariable String commentNo, Model model) throws Exception {
	    JsonObject json = new JsonObject();
	    
	    json.addProperty("result", "success");
	    json.addProperty("value", true);
	    json.addProperty("message", "");
        
	    return json;
    }
}
