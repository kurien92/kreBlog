package net.kurien.blog.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String id;
    private String password;
    private String nick;
    private List<GrantedAuthority> authorities;
    private boolean block;

    public void setAuthorities(List<String> authorities) {
        if(authorities == null) {
            authorities.add("ANONYMOUS");
        }

        if(authorities.size() == 0) {
            authorities.add("ANONYMOUS");
        }

        List<GrantedAuthority> authoritiesList = new ArrayList<>();

        for(String authority : authorities) {
            authoritiesList.add(new SimpleGrantedAuthority("ROLE_" + authority));
        }

        this.authorities = authoritiesList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String id) {
        this.id = id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !block;
    }
}
