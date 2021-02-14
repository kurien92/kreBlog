package net.kurien.blog.module.account.service;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.account.entity.Account;

import javax.mail.MessagingException;
import java.util.List;

public interface AccountService {
    Account get(String accountId);
    Account get(Integer accountNo);
    Account getByEmail(String accountEmail);

    List<Account> getList(SearchCriteria criteria);
    void signUp(Account account) throws InvalidRequestException;
    void sendCertKey(String accountEmail, String certKey) throws MessagingException;
    void add(Account account);
    void update(Account account);
    void delete(String accountId);
    void delete(Integer accountNo);
    void passwordChange(Account account) throws InvalidRequestException;

    void checkId(String accountId) throws InvalidRequestException;
    void checkPassword(String accountPassword) throws InvalidRequestException;
    void checkEmail(String accountEmail) throws InvalidRequestException;
    void checkNickname(String accountNickname) throws InvalidRequestException;

    boolean isExistById(String accountId);
    boolean isExistByEmail(String accountEmail);
    boolean isExistByNickname(String accountNickname);
}
