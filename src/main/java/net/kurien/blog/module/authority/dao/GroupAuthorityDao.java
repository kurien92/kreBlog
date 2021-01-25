package net.kurien.blog.module.authority.dao;

import net.kurien.blog.module.authority.entity.GroupAuthority;

import java.util.List;

public interface GroupAuthorityDao {
    List<GroupAuthority> selectGroupAuthorities(String accountId);
    void insertGroup(GroupAuthority groupAuthority);
    void insertGroupAuthority(GroupAuthority groupAuthority);
    void insertGroupAccount(GroupAuthority groupAuthority);
    void deleteGroup(String groupId);
    void deleteGroupAuthority(GroupAuthority groupAuthority);
    void deleteGroupAccount(GroupAuthority groupAuthority);
    void deleteAll();
}
