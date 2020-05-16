package net.kurien.blog.module.comment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {
	private String name;
	private String password;
	private String text;
}
