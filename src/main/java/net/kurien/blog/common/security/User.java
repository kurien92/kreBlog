package net.kurien.blog.common.security;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class User implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = 1L;

    private String id;
    private String password;
    private String nick;
    private boolean block;
    private Collection<? extends GrantedAuthority> authorities;

    public User(String id, String password, String nick, boolean block, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.nick = nick;
        this.block = block;
        this.authorities = authorities;
    }

    public String getUsername() {
        return this.id;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getNick() {
        return this.nick;
    }

    public boolean getBlock() {
        return this.block;
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

    public void eraseCredentials() {
        this.password = null;
    }
}
