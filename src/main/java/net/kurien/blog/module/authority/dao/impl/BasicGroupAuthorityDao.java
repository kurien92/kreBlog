package net.kurien.blog.module.authority.dao.impl;

import net.kurien.blog.module.authority.dao.GroupAuthorityDao;
import net.kurien.blog.module.authority.entity.GroupAuthority;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BasicGroupAuthorityDao implements GroupAuthorityDao {
    private final SqlSession sqlSession;
    private final static String mapper = "net.kurien.blog.module.authority.mapper.GroupAuthorityMapper";

    @Autowired
    public BasicGroupAuthorityDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<GroupAuthority> selectGroupAuthorities(String accountId) {
        Map<String, Object> param = new HashMap<>();
        param.put("accountId", accountId);

        return sqlSession.selectList(mapper + ".selectGroupAuthorities", param);
    }

    @Override
    public void insertGroup(GroupAuthority groupAuthority) {
        sqlSession.insert(mapper + ".insertGroup", groupAuthority);
    }

    @Override
    public void insertGroupAuthority(GroupAuthority groupAuthority) {
        sqlSession.insert(mapper + ".insertGroupAuthority", groupAuthority);
    }

    @Override
    public void insertGroupAccount(GroupAuthority groupAuthority) {
        sqlSession.insert(mapper + ".insertGroupAccount", groupAuthority);
    }

    @Override
    public void deleteGroup(String groupId) {
        sqlSession.delete(mapper + ".deleteGroup", groupId);
    }

    @Override
    public void deleteGroupAuthority(GroupAuthority groupAuthority) {
        sqlSession.delete(mapper + ".deleteGroupAuthority", groupAuthority);
    }

    @Override
    public void deleteGroupAccount(GroupAuthority groupAuthority) {
        sqlSession.delete(mapper + ".deleteGroupAccount", groupAuthority);
    }

    @Override
    public void deleteAll() {
        sqlSession.delete(mapper + ".deleteAllGroups");
        sqlSession.delete(mapper + ".deleteAllGroupAccounts");
        sqlSession.delete(mapper + ".deleteAllGroupAuthorities");
    }
}