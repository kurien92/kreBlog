package net.kurien.blog.module.account.service.impl;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.account.dao.AccountDao;
import net.kurien.blog.module.account.entity.Account;
import net.kurien.blog.module.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicAccountService implements AccountService {
    private final AccountDao accountDao;

    @Autowired
    public BasicAccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account get(String accountId) {
        return accountDao.select(accountId);
    }

    @Override
    public Account get(Integer accountNo) {
        return accountDao.select(accountNo);
    }

    @Override
    public List<Account> getList(SearchCriteria criteria) {
        return accountDao.selectList(criteria);
    }

    @Override
    public void signUp(Account account) {
        accountDao.insert(account);
    }

    @Override
    public void add(Account account) {
        accountDao.insert(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void delete(String accountId) {
        accountDao.delete(accountId);
    }

    @Override
    public void delete(Integer accountNo) {
        accountDao.delete(accountNo);
    }
}
