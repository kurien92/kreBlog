package net.kurien.blog.module.visitor.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Visitor {
	private Integer visitorNo;
	private String userCookie;
	private String userAgent;
	private String currentUrl;
	private String referrer;
	private Integer resolutionX;
	private Integer resolutionY;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date visitTime;
	private String visitorIp;
}
