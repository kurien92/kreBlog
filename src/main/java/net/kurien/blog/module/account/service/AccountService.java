package net.kurien.blog.module.account.service;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.account.entity.Account;

import java.util.List;

public interface AccountService {
    Account get(String accountId);
    Account get(Integer accountNo);
    List<Account> getList(SearchCriteria criteria);
    void signUp(Account account) throws InvalidRequestException;
    void sendCertKey(String accountEmail, String certKey);
    void add(Account account);
    void update(Account account);
    void delete(String accountId);
    void delete(Integer accountNo);
    boolean isExistById(String accountId);
    boolean isExistByEmail(String accountEmail);
    boolean isExistByNickname(String accountNickname);
}
