package net.kurien.blog.module.content.service.impl;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.content.dao.ContentDao;
import net.kurien.blog.module.content.entity.Content;
import net.kurien.blog.module.content.service.ContentService;
import net.kurien.blog.module.post.entity.Post;
import net.kurien.blog.module.search.dto.SearchDTO;
import net.kurien.blog.module.search.dto.Searchable;
import net.kurien.blog.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicContentService implements ContentService, Searchable {
    private final ContentDao contentDao;

    @Inject
    public BasicContentService(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    @Override
    public Content create(Content content) {
        content.setContentWriteTime(TimeUtil.currentTime());
        contentDao.insert(content);

        return content;
    }

    @Override
    public void update(Content content) {
        content.setContentWriteTime(TimeUtil.currentTime());

        contentDao.update(content);
    }

    @Override
    public void delete(String contentId) {
        contentDao.delete(contentId);
    }

    @Override
    public Content view(String contentId, String manageYn) {
        return contentDao.selectOne(contentId, manageYn);
    }

    @Override
    public List<Content> list(String manageYn, SearchCriteria searchCriteria) {
        return contentDao.selectList(manageYn, searchCriteria);
    }

    @Override
    public int getCount(String manageYn) {
        return contentDao.selectCount(manageYn);
    }

    @Override
    public void deleteAll() {
        contentDao.deleteAll();
    }

    @Override
    public SearchDTO search(String[] queries) {
        List<Map<String, Object>> searchContents = new ArrayList<>();
        SearchDTO searchDto = new SearchDTO();

        List<Content> contents = contentDao.search(queries);

        for(Content content : contents) {
            Map<String, Object> searchContent = new LinkedHashMap<>();

            searchContent.put("id", content.getContentId());
            searchContent.put("name", "");
            searchContent.put("title", content.getContentTitle());
            searchContent.put("description", content.getContent());

            searchContents.add(searchContent);
        }

        searchDto.setTitle("CONTENT");
        searchDto.setContents(searchContents);

        return searchDto;
    }
}
