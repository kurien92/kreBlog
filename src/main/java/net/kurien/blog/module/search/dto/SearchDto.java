package net.kurien.blog.module.search.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SearchDto {
    private String title;
    private List<Map<String, Object>> contents;
}
