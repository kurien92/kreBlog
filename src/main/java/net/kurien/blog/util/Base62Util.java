package net.kurien.blog.util;

import org.springframework.stereotype.Component;

@Component
public class Base62Util {
	private static final char[] BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	
	public static String encode(long value) {
		StringBuilder sb = new StringBuilder();
		
		do {
			int i = (int)(value % 62);
			sb.append(BASE62[i]);
			value /= 62;
		} while(value > 0);
		
		return sb.toString();
	}
	
	public static long decode(String base62String) {
		long result = 0;
		long power = 1;
		
		String base62CharString = new String(BASE62);
		
		for(int i = 0; i < base62String.length(); i++) {
			long digit = base62CharString.indexOf(base62String.charAt(i));
			
			result += digit * power;
			power *= 62;
		}
		
		return result;
	}
}
