package net.kurien.blog.module.autosave.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ServiceAutosave {
    private String serviceName;
    private Long serviceNo;
    private Long asNo;
    private String serviceAsUsername;
    private Date serviceAsWriteTime;
    private String serviceAsWriteIp;
    private Date ServiceAsExpireTime;
}
