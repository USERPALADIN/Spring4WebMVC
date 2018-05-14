package com.mitya.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
//
//public class CustomUserDetails extends  User implements UserDetails {
//
//    public CustomUserDetails(String name, String login, String password, String role) {
//        super(name, login, password, role);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//    }
//
//    @Override
//    public String getUsername() {
//        return super.getLogin();
//    }
//
//    @Override
//    public String getPassword() {
//        return super.getPassword();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
