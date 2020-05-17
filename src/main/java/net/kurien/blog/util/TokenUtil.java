package net.kurien.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import net.kurien.blog.vo.Token;

@Component
public class TokenUtil {
	@SuppressWarnings("unchecked")
	public static String createToken(HttpServletRequest request, String tokenType, int expirationMillisecondTime) throws NoSuchAlgorithmException {
		Map<String, Token> tokenMap = getTokenMap(request, tokenType);
		
		String tokenKey = getTokenString();
		Date tokenExpirationTime = new Date(Calendar.getInstance().getTimeInMillis() + expirationMillisecondTime);
		
		Token token = new Token();
		token.setKey(tokenKey);
		token.setExpirationTime(tokenExpirationTime);
		
		tokenMap.put(tokenKey, token);
		
		return tokenKey;
	}
	
	public static boolean checkToken(HttpServletRequest request, String tokenType, String tokenKey) {
		Map<String, Token> tokenMap = getTokenMap(request, tokenType);
		
		Token token = tokenMap.get(tokenKey);
		
		if(token == null) {
			return false;
		}
		
		if(Calendar.getInstance().getTimeInMillis() > token.getExpirationTime().getTime()) {
			return false;
		}
		
		return true;
	}
	
	private static Map<String, Token> getTokenMap(HttpServletRequest request, String tokenType) {
		tokenType = "token_" + tokenType;
		
		Map<String, Token> tokenMap = null;
		
		HttpSession session = request.getSession();
		Object tokenSession = session.getAttribute(tokenType);
		
		if(tokenSession == null) {
			tokenMap = new HashMap<>();
			session.setAttribute(tokenType, tokenMap);
		} else {
			tokenMap = (Map<String, Token>)tokenSession;
		}
		
		return tokenMap;
	}
	
	private static String getTokenString() throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		String randomizeName = UUID.randomUUID().toString();
		md.update(randomizeName.getBytes());
		
		byte[] mdBytes = md.digest();
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < mdBytes.length; i++) {
			sb.append(Integer.toString((mdBytes[i]&0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
}
