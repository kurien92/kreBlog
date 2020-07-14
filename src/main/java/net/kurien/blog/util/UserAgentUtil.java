package net.kurien.blog.util;

import java.io.IOException;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;

public class UserAgentUtil {
	private static UserAgentParser parser = null;
	
	public static Capabilities parseUserAgent(String userAgent) throws IOException, ParseException {
		if(parser == null) {
			parser = new UserAgentService().loadParser();			
		}

		return parser.parse(userAgent);
	}
}
