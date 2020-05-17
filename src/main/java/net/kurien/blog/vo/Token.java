package net.kurien.blog.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Token {
	private String key;
	private Date expirationTime;
}
