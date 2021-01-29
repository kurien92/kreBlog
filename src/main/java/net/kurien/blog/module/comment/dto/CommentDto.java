package net.kurien.blog.module.comment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {
	private Integer no;
	private String name;
	private String password;
	private String text;
}
