package net.kurien.blog.module.file.entity;

import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class File {
    private Integer fileNo;
    private String fileType;
    private String filePath;
    private String fileName;
    private String fileStoredName;
    private String fileExtension;
    private String fileMime;
    private Long fileSize;
    private String fileUploadIp;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fileUploadTime;
}