package net.kurien.blog.module.authority.dao;

import net.kurien.blog.module.authority.entity.Authority;

import java.util.List;

public interface AuthorityDao {
    List<Authority> selectList(String accountId);
    void insert(Authority authority);
    void delete(Authority authority);
}
