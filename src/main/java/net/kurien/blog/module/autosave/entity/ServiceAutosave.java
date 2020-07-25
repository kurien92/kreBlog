package net.kurien.blog.module.autosave.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ServiceAutosave {
    private String serviceName;
    private Long asNo;
    private String serviceAsUsername;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serviceAsWriteTime;
    private String serviceAsWriteIp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serviceAsExpireTime;
}
