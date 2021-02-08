package net.kurien.blog.module.account.dao.impl;

import net.kurien.blog.common.type.TrueFalseType;
import net.kurien.blog.module.account.dao.AccountDao;
import net.kurien.blog.module.account.entity.Account;
import net.kurien.blog.util.EncryptionUtil;
import net.kurien.blog.util.TimeUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/*-context.xml"})
@WebAppConfiguration
public class BasicAccountDaoTest {
    @Inject
    private AccountDao accountDao;

    private Account account1;

    @Before
    public void setUp() throws Exception {
        accountDao.deleteAll();

        account1 = new Account();
        account1.setAccountNo(1);
        account1.setAccountId("kurien");
        account1.setAccountPassword(EncryptionUtil.hashPassword("test"));
        account1.setAccountEmail("kurien92@gmail.com");
        account1.setAccountNickname("Kurien");
        account1.setAccountBlock(TrueFalseType.FALSE);
        account1.setAccountSignUpDate(TimeUtil.currentTime());
        account1.setAccountSignUpIp("127.0.0.1");
        account1.setAccountCertYn(TrueFalseType.FALSE);
        account1.setAccountCertDate(null);
        account1.setAccountCertKey("");

        accountDao.insert(account1);
    }

    @Test
    public void selectList() {
        assertThat(accountDao.selectList(null).size(), is(1));
    }

    @Test
    public void select() {
        assertThat(accountDao.select("kurien"), is(account1));
    }

    @Test
    public void testSelect() {
        assertThat(accountDao.select(1), is(account1));
    }

    @Test
    public void insert() {
        Account account2 = new Account();
        account2.setAccountNo(2);
        account2.setAccountId("kre29");
        account2.setAccountPassword(EncryptionUtil.hashPassword("test"));
        account2.setAccountEmail("kurien@naver.com");
        account2.setAccountNickname("KRE");
        account2.setAccountBlock(TrueFalseType.FALSE);
        account2.setAccountSignUpDate(TimeUtil.currentTime());
        account2.setAccountSignUpIp("127.0.0.1");
        account2.setAccountCertYn(TrueFalseType.FALSE);
        account2.setAccountCertDate(null);
        account2.setAccountCertKey("");

        accountDao.insert(account2);

        assertThat(accountDao.select("kre29"), is(account2));
    }

    @Test
    public void update() {
        account1.setAccountBlock(TrueFalseType.TRUE);

        accountDao.update(account1);

        assertThat(accountDao.select("kurien"), is(account1));
    }

    @Test
    public void delete() {
        accountDao.delete(account1.getAccountId());
        assertNull(accountDao.select("kurien"));
    }

//    @Test
//    public void testDelete() {
//        accountDao.delete(account1.getAccountNo());
//
//        assertNull(accountDao.select("kurien"));
//    }
}