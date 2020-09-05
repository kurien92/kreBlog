package net.kurien.blog.module.content.service;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.content.entity.Content;

import java.util.List;

public interface ContentService {
    Content create(Content content);
    void update(Content content);
    void delete(String contentId);
    Content view(String contentId, String manageYn);
    List<Content> list(String manageYn, SearchCriteria searchCriteria);
    int getCount(String manageYn);
    void deleteAll();
}
