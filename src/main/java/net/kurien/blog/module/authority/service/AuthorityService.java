package net.kurien.blog.module.authority.service;

import net.kurien.blog.module.authority.entity.Authority;
import net.kurien.blog.module.authority.entity.GroupAuthority;

import java.util.List;

public interface AuthorityService {
    List<Authority> getList(String accountId);
    void add(Authority authority);
    void delete(Authority authority);
    List<GroupAuthority> getGroupList(String accountId);
    void addGroup(GroupAuthority groupAuthority);
    void addGroupAuthority(GroupAuthority groupAuthority);
    void addGroupAccount(GroupAuthority groupAuthority);
    void deleteGroup(String groupId);
    void deleteGroupAuthority(GroupAuthority groupAuthority);
    void deleteGroupAccount(GroupAuthority groupAuthority);
    void deleteAll();
}
