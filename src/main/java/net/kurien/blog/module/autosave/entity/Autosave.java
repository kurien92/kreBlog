package net.kurien.blog.module.autosave.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Autosave {
    private Long asNo;
    private String asJsonData;
    private Date asSaveTime;
}
