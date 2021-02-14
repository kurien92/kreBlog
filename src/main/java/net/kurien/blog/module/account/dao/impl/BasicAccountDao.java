package net.kurien.blog.module.account.dao.impl;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.account.dao.AccountDao;
import net.kurien.blog.module.account.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BasicAccountDao implements AccountDao {
    private final SqlSession sqlSession;
    private final static String mapper = "net.kurien.blog.module.account.mapper.AccountMapper";

    @Autowired
    public BasicAccountDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Account> selectList(SearchCriteria criteria) {
        return sqlSession.selectList(mapper + ".selectList", criteria);
    }

    @Override
    public Account select(String accountId) {
        Map<String, Object> param = new HashMap<>();
        param.put("accountId", accountId);
        return sqlSession.selectOne(mapper + ".selectOne", param);
    }

    @Override
    public Account select(Integer accountNo) {
        Map<String, Object> param = new HashMap<>();
        param.put("accountNo", accountNo);
        return sqlSession.selectOne(mapper + ".selectOne", param);
    }

    @Override
    public void insert(Account account) {
        sqlSession.insert(mapper + ".insert", account);
    }

    @Override
    public void update(Account account) {
        sqlSession.update(mapper + ".update", account);
    }

    @Override
    public void delete(String accountId) {
        Map<String, Object> param = new HashMap<>();
        param.put("accountId", accountId);
        sqlSession.delete(mapper + ".delete", param);
    }

    @Override
    public void delete(Integer accountNo) {
        Map<String, Object> param = new HashMap<>();
        param.put("accountNo", accountNo);
        sqlSession.delete(mapper + ".delete", param);
    }

    @Override
    public void deleteAll() {
        sqlSession.insert(mapper + ".deleteAll");
    }

    @Override
    public int isExistById(String accountId) {
        return sqlSession.selectOne(mapper + ".isExistById", accountId);
    }

    @Override
    public int isExistByEmail(String accountEmail) {
        return sqlSession.selectOne(mapper + ".isExistByEmail", accountEmail);
    }

    @Override
    public int isExistByNickname(String accountNickname) {
        return sqlSession.selectOne(mapper + ".isExistByNickname", accountNickname);
    }

    @Override
    public Account selectByEmail(String accountEmail) {
        return sqlSession.selectOne(mapper + ".selectByEmail", accountEmail);
    }

    @Override
    public void updatePassword(Account account) {
        sqlSession.update(mapper + ".updatePassword", account);
    }
}
