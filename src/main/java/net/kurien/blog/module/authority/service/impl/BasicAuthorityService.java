package net.kurien.blog.module.authority.service.impl;

import net.kurien.blog.module.authority.dao.AuthorityDao;
import net.kurien.blog.module.authority.entity.Authority;
import net.kurien.blog.module.authority.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicAuthorityService implements AuthorityService {
    private final AuthorityDao authorityDao;

    public BasicAuthorityService(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

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
