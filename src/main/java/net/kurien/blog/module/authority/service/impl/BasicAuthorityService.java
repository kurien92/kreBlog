package net.kurien.blog.module.authority.service.impl;

import net.kurien.blog.module.authority.dao.AuthorityDao;
import net.kurien.blog.module.authority.dao.GroupAuthorityDao;
import net.kurien.blog.module.authority.entity.Authority;
import net.kurien.blog.module.authority.entity.GroupAuthority;
import net.kurien.blog.module.authority.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicAuthorityService implements AuthorityService {
    private final AuthorityDao authorityDao;
    private final GroupAuthorityDao groupAuthorityDao;

    @Autowired
    public BasicAuthorityService(AuthorityDao authorityDao, GroupAuthorityDao groupAuthorityDao) {
        this.authorityDao = authorityDao;
        this.groupAuthorityDao = groupAuthorityDao;
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

    @Override
    public List<GroupAuthority> getGroupList(String accountId) {
        return groupAuthorityDao.selectGroupAuthorities(accountId);
    }

    @Override
    public void addGroup(GroupAuthority groupAuthority) {
        groupAuthorityDao.insertGroup(groupAuthority);
    }

    @Override
    public void addGroupAuthority(GroupAuthority groupAuthority) {
        groupAuthorityDao.insertGroupAuthority(groupAuthority);
    }

    @Override
    public void addGroupAccount(GroupAuthority groupAuthority) {
        groupAuthorityDao.insertGroupAccount(groupAuthority);
    }

    @Override
    public void deleteGroup(String groupId) {
        groupAuthorityDao.deleteGroup(groupId);
    }

    @Override
    public void deleteGroupAuthority(GroupAuthority groupAuthority) {
        groupAuthorityDao.deleteGroupAuthority(groupAuthority);
    }

    @Override
    public void deleteGroupAccount(GroupAuthority groupAuthority) {
        groupAuthorityDao.deleteGroupAccount(groupAuthority);
    }

    @Override
    public void deleteAll() {
        groupAuthorityDao.deleteAll();
    }
}
