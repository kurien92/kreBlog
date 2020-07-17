package net.kurien.blog.module.autosave.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Autosave {
    private Long asNo;
    private String asJsonData;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date asSaveTime;
}
