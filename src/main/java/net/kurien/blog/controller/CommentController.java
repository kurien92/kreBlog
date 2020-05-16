package net.kurien.blog.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.comment.dto.CommentDto;
import net.kurien.blog.module.comment.service.CommentService;
import net.kurien.blog.module.comment.vo.Comment;
import net.kurien.blog.module.token.vo.Token;
import net.kurien.blog.util.RequestUtil;
import net.kurien.blog.util.TokenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Inject
	private CommentService commentService;
	
	@RequestMapping(value = "/list/{postNo}", method = RequestMethod.GET)
	public JsonObject list(@PathVariable int postNo) {
	    JsonObject json = new JsonObject();

		List<Comment> comments = commentService.getList(postNo);
		
		JsonArray commentJsonArray = new JsonArray(); 
		
		for(int i = 0; i < comments.size(); i++) {
			commentJsonArray.add(getJsonFromComment(comments.get(i)));
		}

	    json.addProperty("result", "success");
	    json.add("value", commentJsonArray);
	    json.addProperty("message", "");
		
		return json;
	}
	
	@RequestMapping(value = "/write/{postNo}", method = RequestMethod.POST)
	public JsonObject write(@PathVariable int postNo, CommentDto commentDto, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
	    JsonObject json = new JsonObject();
	    
		try {
			validInput(commentDto);
		} catch(InvalidRequestException ire) {
		    json.addProperty("result", "success");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "");
		    return json;
		}
		
		Comment comment = new Comment();
		
		comment.setPostNo(postNo);
		comment.setAuthor(commentDto.getName());
		comment.setPassword(commentDto.getPassword());
		comment.setComment(commentDto.getText());
		comment.setWriteIp(RequestUtil.getRemoteAddr(request));
		
		commentService.write(comment);
	    
	    json.addProperty("result", "success");
	    json.add("value", getJsonFromComment(comment));
	    json.addProperty("message", "");
        
	    return json;
	}
	
	private boolean validInput(CommentDto commentDto) throws InvalidRequestException {
		// TODO Auto-generated method stub
		if(commentDto.getName().length() < 2) {
			throw new InvalidRequestException("작성자명은 2자 이상으로 입력해주세요.");
		}
		
		if(commentDto.getName().length() > 30) {
			throw new InvalidRequestException("작성자명은 30자 미만으로 입력해주세요.");
		}
		
		if(commentDto.getPassword().length() == 0) {
			throw new InvalidRequestException("비밀번호를 입력해주세요.");
		}
		
		if(commentDto.getPassword().length() > 30) {
			throw new InvalidRequestException("비밀번호는 30자 미만으로 입력해주세요.");
		}
		
		if(commentDto.getPassword().length() == 0) {
			throw new InvalidRequestException("댓글을 입력해주세요.");
		}
		
		if(commentDto.getPassword().length() > 30) {
			throw new InvalidRequestException("댓글은 10000자 미만으로 입력해주세요.");
		}

		return true;
	}

	@RequestMapping(value = "/reply/{no}", method = RequestMethod.POST)
	public JsonObject reply(@PathVariable int no, CommentDto commentDto, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		JsonObject json = new JsonObject();
		
		try {
			validInput(commentDto);
		} catch(InvalidRequestException ire) {
		    json.addProperty("result", "success");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "");
		    return json;
		}
		
		Comment comment = new Comment();
		
		comment.setAuthor(commentDto.getName());
		comment.setPassword(commentDto.getPassword());
		comment.setComment(commentDto.getText());
		comment.setWriteIp(RequestUtil.getRemoteAddr(request));

		try {
			commentService.reply(no, comment);
		} catch(InvalidRequestException ire) {
		    json.addProperty("result", "fail");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "잘못된 요청입니다.");
		    
		    return json;
		}
		
	    json.addProperty("result", "success");
	    json.add("value", getJsonFromComment(comment));
	    json.addProperty("message", "");
        
	    return json;
    }
    
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public JsonObject modify(@PathVariable int no, CommentDto commentDto, String token, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		JsonObject json = new JsonObject();
		
	    if(TokenUtil.checkToken(request, "comment", token) == false) {
		    json.addProperty("result", "fail");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "수정 가능한 시간이 만료되었습니다.");
		    
		    return json;
	    }
		
		try {
			validInput(commentDto);
		} catch(InvalidRequestException ire) {
		    json.addProperty("result", "success");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "");
		    return json;
		}

		Comment comment = new Comment();
		
		comment.setCommentNo(no);
		comment.setAuthor(commentDto.getName());
		comment.setPassword(commentDto.getPassword());
		comment.setComment(commentDto.getText());
		comment.setWriteIp(RequestUtil.getRemoteAddr(request));
	    
	    commentService.modify(comment);
	    
	    comment = commentService.get(no);
	    
	    json.addProperty("result", "success");
	    json.add("value", getJsonFromComment(comment));
	    json.addProperty("message", "");
        
	    return json;
    }
    
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public JsonObject delete(@PathVariable int no, String token, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
	    JsonObject json = new JsonObject();

	    if(TokenUtil.checkToken(request, "comment", token) == false) {
		    json.addProperty("result", "fail");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "삭제 가능한 시간이 만료되었습니다.");
		    
		    return json;
	    }
	    
	    commentService.delete(no);
	    
	    json.addProperty("result", "success");
	    json.addProperty("value", "");
	    json.addProperty("message", "");
        
	    return json;
    }
    
	@RequestMapping(value = "/passwordCheck/{no}", method = RequestMethod.POST)
	public JsonObject passwordCheck(@PathVariable int no, String password, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		boolean checkedPassword = commentService.checkPassword(no, password);
		
		JsonObject json = new JsonObject();
		
		if(checkedPassword == false) {
		    json.addProperty("result", "fail");
		    json.add("value", new JsonObject());
		    json.addProperty("message", "비밀번호가 일치하지 않습니다.");
		    
		    return json;
		}
		
		Comment comment = commentService.get(no);
		
		JsonObject valueObject = getJsonFromComment(comment);

	    valueObject.addProperty("token", TokenUtil.createToken(request, "comment", 21600000));

	    json.addProperty("result", "success");
	    json.add("value", valueObject);
	    json.addProperty("message", "");

	    return json;
    }

	private JsonObject getJsonFromComment(Comment comment) {
        JsonObject commentJson = new JsonObject();
        
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    
	    String text = comment.getComment();
	    
	    if(comment.getDeleteYn().equals("Y")) {
	    	text = "";
	    }

	    commentJson.addProperty("no", comment.getCommentNo());
	    commentJson.addProperty("name", comment.getAuthor());
		commentJson.addProperty("time", sdf.format(comment.getWriteTime()));
		commentJson.addProperty("text", text);
		commentJson.addProperty("depth", comment.getCommentDepth());
		commentJson.addProperty("delYn", comment.getDeleteYn());
		
		return commentJson;
	}
}
