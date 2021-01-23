package net.kurien.blog.module.authority.service.impl;

import net.kurien.blog.module.authority.dao.AuthorityDao;
import net.kurien.blog.module.authority.entity.Authority;
import net.kurien.blog.module.authority.service.AuthorityService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BasicAuthorityService implements AuthorityService {
    @Inject
    private AuthorityDao authorityDao;

    @Override
    public List<Authority> getList(String accountId) {
        return authorityDao.selectList(accountId);
    }

    @Override
    public void add(Authority authority) {
        authorityDao.insert(authority);
    }

    @Override
    public void delete(Authority authority) {
        authorityDao.delete(authority);
    }
}
