package net.kurien.blog.module.token.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Token {
	private String key;
	private Date expirationTime;
}
