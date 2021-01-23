package net.kurien.blog.module.authority.service;

import net.kurien.blog.module.authority.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> getList(String accountId);
    void add(Authority authority);
    void delete(Authority authority);
}
