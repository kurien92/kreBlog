package net.kurien.blog.module.account.service.impl;

import net.kurien.blog.common.type.TrueFalseType;
import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.exception.InvalidRequestException;
import net.kurien.blog.module.account.dao.AccountDao;
import net.kurien.blog.module.account.entity.Account;
import net.kurien.blog.module.account.service.AccountService;
import net.kurien.blog.module.mail.service.MailService;
import net.kurien.blog.util.EncryptionUtil;
import net.kurien.blog.util.TimeUtil;
import net.kurien.blog.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class BasicAccountService implements AccountService {
    private final AccountDao accountDao;

    private final MailService mailService;

    @Autowired
    public BasicAccountService(AccountDao accountDao, MailService mailService) {
        this.accountDao = accountDao;
        this.mailService = mailService;
    }

    @Override
    public Account get(String accountId) {
        return accountDao.select(accountId);
    }

    @Override
    public Account get(Integer accountNo) {
        return accountDao.select(accountNo);
    }

    public Account getByEmail(String accountEmail) {
        return accountDao.selectByEmail(accountEmail);
    }

    @Override
    public List<Account> getList(SearchCriteria criteria) {
        return accountDao.selectList(criteria);
    }

    @Override
    public void signUp(Account account) throws InvalidRequestException {
        checkId(account.getAccountId());
        checkPassword(account.getAccountPassword());
        checkEmail(account.getAccountEmail());
        checkNickname(account.getAccountNickname());

        account.setAccountPassword(EncryptionUtil.hashPassword(account.getAccountPassword()));
        account.setAccountBlock(TrueFalseType.FALSE);
        account.setAccountSignUpDate(TimeUtil.currentTime());

        accountDao.insert(account);
    }

    @Override
    public void sendCertKey(String accountEmail, String certKey) throws MessagingException {
        mailService.send("kurien92@gmail.com", accountEmail, "Kurien's Blog 인증메일입니다.", "인증번호는 '" + certKey + "' 입니다.");
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

    public void passwordChange(Account account) throws InvalidRequestException {
        if(ValidationUtil.password(account.getAccountPassword()) == false) {
            throw new InvalidRequestException("비밀번호 형식에 맞지 않습니다. 확인 후 다시시도하여주시기 바랍니다.");
        }

        Account selectedAccount = accountDao.selectByEmail(account.getAccountEmail());

        if(EncryptionUtil.checkPassword(account.getAccountPassword(), selectedAccount.getAccountPassword()) == true) {
            throw new InvalidRequestException("이미 사용중인 비밀번호입니다. 다른 비밀번호를 입력해주세요.");
        }

        account.setAccountPassword(EncryptionUtil.hashPassword(account.getAccountPassword()));

        accountDao.updatePassword(account);
    }

    public void checkId(String accountId) throws InvalidRequestException {
        if (ValidationUtil.length(accountId, 4, 30) == false) {
            throw new InvalidRequestException("아이디의 길이를 확인하여주시기 바랍니다.");
        }

        if (isExistById(accountId)) {
            throw new InvalidRequestException("중복된 아이디가 있습니다. 다른 아이디을 입력해주세요.");
        }
    }

    public void checkPassword(String accountPassword) throws InvalidRequestException {
        if(ValidationUtil.password(accountPassword) == false) {
            throw new InvalidRequestException("비밀번호 형식에 맞지 않습니다. 확인 후 다시시도하여주시기 바랍니다.");
        }
    }

    public void checkEmail(String accountEmail) throws InvalidRequestException {
        if (ValidationUtil.email(accountEmail) == false) {
            throw new InvalidRequestException("이메일 형식이 아닙니다. 확인 후 다시시도하여주시기 바랍니다.");
        }

        if (isExistByEmail(accountEmail)) {
            throw new InvalidRequestException("중복된 이메일이 있습니다. 다른 이메일을 입력해주세요.");
        }
    }

    public void checkNickname(String accountNickname) throws InvalidRequestException {
        if(ValidationUtil.length(accountNickname, 3, 20) == false) {
            throw new InvalidRequestException("닉네임의 길이를 확인하여주시기 바랍니다.");
        }


        if(isExistByNickname(accountNickname)) {
            throw new InvalidRequestException("중복된 닉네임이 있습니다. 다른 닉네임을 입력해주세요.");
        }
    }

    public boolean isExistById(String accountId) {
        if(accountDao.isExistById(accountId) == 0) {
            return false;
        }

        return true;
    }

    public boolean isExistByEmail(String accountEmail) {
        if(accountDao.isExistByEmail(accountEmail) == 0) {
            return false;
        }

        return true;
    }

    public boolean isExistByNickname(String accountNickname) {
        if(accountDao.isExistByNickname(accountNickname) == 0) {
            return false;
        }

        return true;
    }
}
