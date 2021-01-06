package net.kurien.blog.module.content.service;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.DuplicatedKeyException;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.content.entity.Content;

import java.util.List;

public interface ContentService {
    Content get(String contentId, String manageYn) throws NotFoundDataException;
    List<Content> getList(String manageYn, SearchCriteria searchCriteria);
    Content create(Content content, Integer[] fileNos) throws DuplicatedKeyException;
    void update(Content content, Integer[] fileNos);
    void delete(String contentId);
    int getCount(String manageYn);
    void deleteAll();
    boolean isExist(String contentId, String manageYn);
}
