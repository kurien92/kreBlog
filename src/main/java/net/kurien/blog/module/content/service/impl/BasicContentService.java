package net.kurien.blog.module.content.service.impl;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.DuplicatedKeyException;
import net.kurien.blog.exception.NotFoundDataException;
import net.kurien.blog.module.content.dao.ContentDao;
import net.kurien.blog.module.content.entity.Content;
import net.kurien.blog.module.content.service.ContentService;
import net.kurien.blog.module.file.service.ServiceFileService;
import net.kurien.blog.module.search.dto.SearchDto;
import net.kurien.blog.module.search.dto.Searchable;
import net.kurien.blog.module.sitemap.SitemapCreatable;
import net.kurien.blog.module.sitemap.SitemapDto;
import net.kurien.blog.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BasicContentService implements ContentService, Searchable, SitemapCreatable {
    private final ContentDao contentDao;
    private final ServiceFileService serviceFileService;

    @Autowired
    public BasicContentService(ContentDao contentDao, ServiceFileService serviceFileService) {
        this.contentDao = contentDao;
        this.serviceFileService = serviceFileService;
    }

    @Override
    public Content get(String contentId, String manageYn) throws NotFoundDataException {
        if(!isExist(contentId, manageYn)) {
            throw new NotFoundDataException(contentId + " 컨텐츠를 찾을 수 없습니다.");
        }

        return contentDao.selectOne(contentId, manageYn);
    }

    @Override
    public List<Content> getList(String manageYn, SearchCriteria searchCriteria) {
        return contentDao.selectList(manageYn, searchCriteria);
    }

    @Override
    public Content create(Content content, Integer[] fileNos) throws DuplicatedKeyException {
        if(isExist(content.getContentId(), "Y")) {
            throw new DuplicatedKeyException(content.getContentId() + " 컨텐츠가 이미 존재합니다.");
        }

        content.setContentWriteTime(TimeUtil.currentTime());
        contentDao.insert(content);

        addServiceFiles(content, fileNos);

        return content;
    }

    @Override
    public void update(Content content, Integer[] fileNos) {
        content.setContentWriteTime(TimeUtil.currentTime());

        contentDao.update(content);

        content = contentDao.selectOne(content.getContentId(), "Y");

        addServiceFiles(content, fileNos);
    }

    @Override
    public void delete(String contentId) {
        Content content = contentDao.selectOne(contentId, "Y");

        serviceFileService.removeFiles("content", content.getContentNo());
        contentDao.delete(contentId);
    }

    @Override
    public int getCount(String manageYn) {
        return contentDao.selectCount(manageYn);
    }

    @Override
    public void deleteAll() {
        serviceFileService.removeFiles("content");
        contentDao.deleteAll();
    }

    @Override
    public boolean isExist(String contentId, String manageYn) {
        // TODO Auto-generated method stub
        int count = contentDao.isExist(contentId, manageYn);

        if(count < 1) {
            return false;
        }

        return true;
    }

    private void addServiceFiles(Content content, Integer[] fileNos) {
        if(fileNos != null) {
            serviceFileService.addFiles("content", content.getContentNo(), fileNos, "");
        }

        Set<Integer> useFilesNo = new HashSet<>();

        List<Pattern> patterns = new ArrayList<>();

        patterns.add(Pattern.compile("['|\"]/file/viewer/content/(\\d+?)['|\"]"));
        patterns.add(Pattern.compile("['|\"]/file/download/content/(\\d+?)['|\"]"));

        for(Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(content.getContent());

            while(matcher.find()) {
                useFilesNo.add(Integer.parseInt(matcher.group(1)));
            }
        }

        serviceFileService.syncFiles("content", content.getContentNo(), useFilesNo, "");
    }

    @Override
    public SearchDto search(String[] queries) {
        List<Map<String, Object>> searchContents = new ArrayList<>();
        SearchDto searchDto = new SearchDto();

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

    @Override
    public List<SitemapDto> sitemap(String siteUrl) {
        List<SitemapDto> sitemapDtos = new ArrayList<>();

        List<Content> contents = contentDao.selectList("N", null);

        for(Content content : contents) {
            SitemapDto sitemapDto = new SitemapDto();

            sitemapDto.setLoc(siteUrl + "/content/" + content.getContentId());
            sitemapDto.setLastmod(content.getContentWriteTime());
            sitemapDto.setChangefreq("daily");
            sitemapDto.setPriority(0.4);

            sitemapDtos.add(sitemapDto);
        }

        return sitemapDtos;
    }
}
