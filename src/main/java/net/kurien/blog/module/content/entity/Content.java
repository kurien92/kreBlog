package net.kurien.blog.module.content.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Content {
    private Integer contentNo;
    private String contentId;
    private String contentTitle;
    private String content;
    private ContentViewStatus contentView;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contentWriteTime;
}
