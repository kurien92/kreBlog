package net.kurien.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.kurien.blog.vo.Token;

public class TokenUtil {
	public static String createToken(HttpServletRequest request, String tokenType, int expirationMillisecondTime) throws NoSuchAlgorithmException {
		String tokenKey = getTokenString();
		Date tokenExpirationTime = new Date(Calendar.getInstance().getTimeInMillis() + expirationMillisecondTime);

		setTokenMap(request, tokenType, tokenKey, tokenExpirationTime);

		return tokenKey;
	}
	
	public static boolean checkToken(HttpServletRequest request, String tokenType, String tokenKey) {
		Token token = getTokenMap(request, tokenType, tokenKey);

		if(token == null) {
			return false;
		}

		return true;
	}
	
	public static String getTokenString() throws NoSuchAlgorithmException {
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

	private static void setTokenMap(HttpServletRequest request, String tokenType, String tokenKey, Date tokenExpirationTime) {
		tokenType = "token_" + tokenType;

		Map<String, Token> tokenMap = new HashMap<>();

		HttpSession session = request.getSession();
		Object tokenSession = session.getAttribute(tokenType);

		if(tokenSession != null) {
			tokenMap = (Map) tokenSession;
		}

		Token token = new Token();
		token.setKey(tokenKey);
		token.setExpirationTime(tokenExpirationTime);

		tokenMap.put(tokenKey, token);

		session.setAttribute(tokenType, tokenMap);
	}
	
	private static Token getTokenMap(HttpServletRequest request, String tokenType, String tokenKey) {
		tokenType = "token_" + tokenType;

		Map<String, Token> tokenMap = null;

		HttpSession session = request.getSession();
		Object tokenSession = session.getAttribute(tokenType);

		if(tokenSession == null) {
			return null;
		}

		tokenMap = (Map<String, Token>)tokenSession;
		Token token = tokenMap.get(tokenKey);

		if(token == null) {
			return null;
		}

		if(Calendar.getInstance().getTimeInMillis() > token.getExpirationTime().getTime()) {
			return null;
		}

		return token;
	}
}
