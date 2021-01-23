package net.kurien.blog.module.account.dao;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.account.entity.Account;

import java.util.List;

public interface AccountDao {
    List<Account> selectList(SearchCriteria criteria);
    Account select(String accountId);
    Account select(Integer accountNo);
    void insert(Account account);
    void update(Account account);
    void delete(String accountId);
    void delete(Integer accountNo);
    void deleteAll();
}
