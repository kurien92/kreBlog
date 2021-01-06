package net.kurien.blog.module.content.dao;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.content.entity.Content;

import java.util.List;

public interface ContentDao {
    void insert(Content content);
    void update(Content content);
    void delete(String contentId);
    Content selectOne(String contentId, String manageYn);
    List<Content> selectList(String manageYn, SearchCriteria searchCriteria);
    int selectCount(String manageYn);
    void deleteAll();
    List<Content> search(String[] queries);
    int isExist(String contentId, String manageYn);
}
