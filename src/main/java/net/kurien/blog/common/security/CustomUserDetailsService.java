package net.kurien.blog.common.security;

import net.kurien.blog.common.type.TrueFalseType;
import net.kurien.blog.module.account.entity.Account;
import net.kurien.blog.module.account.service.AccountService;
import net.kurien.blog.module.authority.entity.Authority;
import net.kurien.blog.module.authority.entity.GroupAuthority;
import net.kurien.blog.module.authority.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountService accountService;
    private final AuthorityService authorityService;

    private boolean enableAuthorities = true;
    private boolean enableGroups = true;

    private String PREFIX = "ROLE_";

    @Autowired
    private CustomUserDetailsService(AccountService accountService, AuthorityService authorityService) {
        this.accountService = accountService;
        this.authorityService = authorityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.get(username);

        if(account == null) {
            throw new UsernameNotFoundException("아이디 또는 비밀번호가 잘못 입력되었습니다.");
        }

        Set<GrantedAuthority> authoritiesSet = new HashSet<>();

        if(this.enableAuthorities) {
            authoritiesSet.addAll(loadUserAuthorities(username));
        }

        if(this.enableGroups) {
            authoritiesSet.addAll(loadGroupAuthorities(username));
        }

        User user = new User(account.getAccountNo(),
                account.getAccountId(),
                account.getAccountPassword(),
                account.getAccountNickname(),
                account.getAccountBlock() == TrueFalseType.TRUE,
                authoritiesSet);

        return user;
    }

    private Collection<GrantedAuthority> loadUserAuthorities(String username) {
        List<Authority> authorities = authorityService.getList(username);

        Collection<GrantedAuthority> authSet = new HashSet<>();

        for(Authority authority : authorities) {
            authSet.add(new SimpleGrantedAuthority(PREFIX + authority.getAuthority()));
        }

        return authSet;
    }

    private Collection<GrantedAuthority> loadGroupAuthorities(String username) {
        List<GroupAuthority> groupAuthorities = authorityService.getGroupList(username);

        Collection<GrantedAuthority> authSet = new HashSet<>();

        for(GroupAuthority groupAuthority : groupAuthorities) {
            authSet.add(new SimpleGrantedAuthority(PREFIX + groupAuthority.getGroupAuthority()));
        }

        return authSet;
    }
}