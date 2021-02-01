package net.kurien.blog.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.kurien.blog.common.security.CurrentUser;
import net.kurien.blog.common.security.User;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.comment.dto.CommentDto;
import net.kurien.blog.module.comment.entity.Comment;
import net.kurien.blog.module.comment.service.CommentService;
import net.kurien.blog.util.HtmlUtil;
import net.kurien.blog.util.RequestUtil;
import net.kurien.blog.util.TokenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	private final CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@RequestMapping(value = "/search/{commentNo}")
	public String search(@PathVariable int commentNo, Model model) {
		Comment comment = commentService.get(commentNo);

		return "redirect: /post/view/" + comment.getPostNo() + "#comment" + comment.getCommentNo();
	}

	@RequestMapping(value = "/list/{postNo}", method = RequestMethod.GET)
	public @ResponseBody JsonObject list(@PathVariable int postNo) {
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
	public @ResponseBody JsonObject write(@PathVariable int postNo,
										  CommentDto commentDto,
										  HttpServletRequest request,
										  HttpServletResponse response,
										  Model model,
										  @CurrentUser User user) throws Exception {
		JsonObject json = new JsonObject();

		try {
			validInput(commentDto, true, user);
		} catch(InvalidRequestException ire) {
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", ire.getMessage());

			return json;
		}

		Comment comment = new Comment();

		comment.setPostNo(postNo);

		if(user == null) {
			comment.setAuthor(HtmlUtil.escapeHtml(commentDto.getName()));
			comment.setPassword(commentDto.getPassword());
		} else {
			comment.setAccountNo(user.getNo());
		}

		comment.setComment(HtmlUtil.escapeHtml(commentDto.getText()));
		comment.setWriteIp(RequestUtil.getRemoteAddr(request));

		commentService.write(comment);

		// 로그인된 유저가 댓글을 작성한 경우 작성자를 로그인된 유저의 닉네임으로 표시한다.
		if(user != null) {
			comment.setAuthor(user.getNick());
		}

		json.addProperty("result", "success");
		json.add("value", getJsonFromComment(comment));
		json.addProperty("message", "");

		return json;
	}

	@RequestMapping(value = "/reply/{no}", method = RequestMethod.POST)
	public @ResponseBody JsonObject reply(@PathVariable int no,
										  CommentDto commentDto,
										  HttpServletRequest request,
										  HttpServletResponse response,
										  Model model,
										  @CurrentUser User user) throws Exception {
		JsonObject json = new JsonObject();

		try {
			validInput(commentDto, true, user);
		} catch(InvalidRequestException ire) {
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", ire.getMessage());

			return json;
		}

		Comment comment = new Comment();

		if(user == null) {
			comment.setAuthor(HtmlUtil.escapeHtml(commentDto.getName()));
			comment.setPassword(commentDto.getPassword());
		} else {
			comment.setAccountNo(user.getNo());
		}

		comment.setComment(HtmlUtil.escapeHtml(commentDto.getText()));
		comment.setWriteIp(RequestUtil.getRemoteAddr(request));

		try {
			commentService.reply(no, comment);
		} catch(InvalidRequestException ire) {
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", ire.getMessage());

			return json;
		}

		// 로그인된 유저가 댓글을 작성한 경우 작성자를 로그인된 유저의 닉네임으로 표시한다.
		if(user != null) {
			comment.setAuthor(user.getNick());
		}

		json.addProperty("result", "success");
		json.add("value", getJsonFromComment(comment));
		json.addProperty("message", "");

		return json;
	}

	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public @ResponseBody JsonObject modify(@PathVariable int no,
										   CommentDto commentDto,
										   String token,
										   HttpServletRequest request,
										   HttpServletResponse response,
										   Model model,
										   @CurrentUser User user) throws Exception {
		JsonObject json = new JsonObject();

		if(TokenUtil.checkToken(request, "comment", token) == false) {
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", "수정 가능한 시간이 만료되었습니다.");

			return json;
		}

		try {
			validInput(commentDto, false, user);
		} catch(InvalidRequestException ire) {
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", ire.getMessage());

			return json;
		}

		Comment comment = new Comment();

		comment.setCommentNo(no);

		if(user == null) {
			comment.setAuthor(HtmlUtil.escapeHtml(commentDto.getName()));
			comment.setPassword(commentDto.getPassword());
		} else {
			comment.setAccountNo(user.getNo());
		}

		comment.setComment(HtmlUtil.escapeHtml(commentDto.getText()));
		comment.setWriteIp(RequestUtil.getRemoteAddr(request));

		commentService.modify(comment);

		comment = commentService.get(no);

		json.addProperty("result", "success");
		json.add("value", getJsonFromComment(comment));
		json.addProperty("message", "");

		return json;
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public @ResponseBody JsonObject delete(@PathVariable int no, String token, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
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

	@RequestMapping(value = "/userCheck/{no}", method = RequestMethod.POST)
	public @ResponseBody JsonObject userCheck(@PathVariable int no,
												  String password,
												  HttpServletRequest request,
												  HttpServletResponse response,
												  Model model,
												  @CurrentUser User user) throws Exception {
		boolean checkedUser = false;
		String failMsg = "";

		if(password != null) {
			failMsg = "비밀번호가 일치하지 않습니다.";
			checkedUser = commentService.checkPassword(no, password);
		} else {
			failMsg = "댓글을 수정/삭제할 권한이 없습니다.";

			try {
				checkedUser = commentService.checkUser(no, user.getNo());
			} catch(NullPointerException e) {
				failMsg = e.getMessage();
			}
		}

		JsonObject json = new JsonObject();

		if(checkedUser == false) {
			json.addProperty("result", "fail");
			json.add("value", new JsonObject());
			json.addProperty("message", failMsg);

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

	
	private boolean validInput(CommentDto commentDto, boolean passwordChangeCheck, User user) throws InvalidRequestException {
		// TODO Auto-generated method stub
		if(user == null) {
			if (commentDto.getName().length() < 2) {
				throw new InvalidRequestException("작성자명은 2자 이상으로 입력해주세요.");
			}

			if (commentDto.getName().length() > 30) {
				throw new InvalidRequestException("작성자명은 30자 미만으로 입력해주세요.");
			}

			if (passwordChangeCheck) {
				if (commentDto.getPassword().length() == 0) {
					throw new InvalidRequestException("비밀번호를 입력해주세요.");
				}
			}

			if (commentDto.getPassword().length() > 30) {
				throw new InvalidRequestException("비밀번호는 30자 미만으로 입력해주세요.");
			}
		}

		if(commentDto.getText().length() == 0) {
			throw new InvalidRequestException("댓글을 입력해주세요.");
		}
		
		if(commentDto.getText().length() > 10000) {
			throw new InvalidRequestException("댓글은 10000자 미만으로 입력해주세요.");
		}

		return true;
	}
}
