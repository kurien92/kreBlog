package net.kurien.blog.module.content.dao.impl;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.content.dao.ContentDao;
import net.kurien.blog.module.content.entity.Content;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BasicContentDao implements ContentDao {
    private final SqlSession sqlSession;

    private final static String mapper = "net.kurien.blog.module.content.mapper.ContentMapper";

    @Inject
    public BasicContentDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void insert(Content content) {
        sqlSession.insert(mapper + ".insert", content);
    }

    @Override
    public void update(Content content) {
        sqlSession.update(mapper + ".update", content);
    }

    @Override
    public void delete(String contentId) {
        sqlSession.delete(mapper + ".delete", contentId);
    }

    @Override
    public Content selectOne(String contentId, String manageYn) {
        Map<String, Object> param = new HashMap<>();
        param.put("contentId", contentId);
        param.put("manageYn", manageYn);

        return sqlSession.selectOne(mapper + ".selectOne", param);
    }

    @Override
    public List<Content> selectList(String manageYn, SearchCriteria searchCriteria) {
        Map<String, Object> param = new HashMap<>();
        param.put("manageYn", manageYn);
        param.put("criteria", searchCriteria);

        return sqlSession.selectList(mapper + ".selectList", param);
    }

    @Override
    public int selectCount(String manageYn) {
        Map<String, Object> param = new HashMap<>();
        param.put("manageYn", manageYn);

        return sqlSession.selectOne(mapper + ".selectCount", param);
    }

    @Override
    public void deleteAll() {
        sqlSession.delete(mapper + ".deleteAll");
    }

    @Override
    public List<Content> search(String[] queries) {
        String[] searchColumns = new String[]{"contentTitle", "content"};

        Map<String, Object> param = new HashMap<>();
        param.put("searchColumns", searchColumns);
        param.put("searchQueries", queries);
        param.put("manageYn", "N");

        return sqlSession.selectList(mapper + ".search", param);
    }

    @Override
    public int isExist(String contentId, String manageYn) {
        // TODO Auto-generated method stub
        Map<String, Object> param = new HashMap<>();
        param.put("contentId", contentId);
        param.put("manageYn", manageYn);

        return sqlSession.selectOne(mapper + ".isExist", param);
    }
}
