package net.kurien.blog.module.file.dto;

import lombok.Data;

@Data
public class FileDTO {
    private Integer no;
    private byte[] bytes;
    private String fileName;
    private long size;
    private String contentType;
}
