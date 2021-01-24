package net.kurien.blog.module.authority.dao.impl;

import net.kurien.blog.module.authority.dao.AuthorityDao;
import net.kurien.blog.module.authority.entity.Authority;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BasicAuthorityDao implements AuthorityDao {
    private final SqlSession sqlSession;
    private final static String mapper = "net.kurien.blog.module.authority.mapper.AuthorityMapper";

    @Autowired
    public BasicAuthorityDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Authority> selectList(String accountId) {
        Map<String, Object> param = new HashMap<>();
        param.put("accountId", accountId);

        return sqlSession.selectList(mapper + ".selectList", param);
    }

    @Override
    public void insert(Authority authority) {
        sqlSession.insert(mapper + ".insert", authority);
    }

    @Override
    public void delete(Authority authority) {
        sqlSession.delete(mapper + ".delete", authority);
    }
}
