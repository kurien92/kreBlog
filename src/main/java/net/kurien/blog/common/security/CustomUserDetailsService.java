package net.kurien.blog.common.security;

import net.kurien.blog.module.account.entity.Account;
import net.kurien.blog.module.account.service.AccountService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Inject
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.get(username);

        if(account == null) {
            throw new UsernameNotFoundException("아이디 또는 비밀번호가 잘못 입력되었습니다.");
        }

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUsername(account.getAccountId());
        userDetails.setPassword(account.getAccountPassword());
        userDetails.setNick(account.getAccountNick());
        userDetails.setBlock(account.getAccountBlock().getValue() == 1);

        return userDetails;
    }
}
