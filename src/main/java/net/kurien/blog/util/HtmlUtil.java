package net.kurien.blog.util;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

@Component
public class HtmlUtil {
	/**
	 * HTML 태그를 제거하고 Text만 남긴 문자열을 리턴한다.
	 * @param htmlString
	 * @return
	 */
	public static String stripHtml(String htmlString) {
		String stripHtml = Jsoup.parse(htmlString).text();
		
		return stripHtml;
	}
	
	/**
	 * HTML 태그를 HTML Entity로 변경한다.
	 * @param htmlString
	 * @return
	 */
	public static String escapeHtml(String htmlString) {
		String htmlEscape = StringEscapeUtils.escapeHtml4(htmlString);

		return htmlEscape;
	}
}
