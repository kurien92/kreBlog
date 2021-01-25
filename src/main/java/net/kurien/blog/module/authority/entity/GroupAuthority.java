package net.kurien.blog.module.authority.entity;

import lombok.Data;

@Data
public class GroupAuthority {
    private String groupId;
    private String groupName;
    private String accountId;
    private String groupAuthority;
}