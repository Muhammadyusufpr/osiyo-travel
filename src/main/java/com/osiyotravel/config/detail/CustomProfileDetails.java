package com.osiyotravel.config.detail;

import com.osiyotravel.enums.ProfileRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;

public class CustomProfileDetails implements UserDetails {

    private String id;

    private ProfileRole role;

    private String username;

    private String phone;

    public CustomProfileDetails(String id, ProfileRole role, String username, String phone) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        if(role.equals(ProfileRole.ROLE_MANAGER)){
            return username;
        }
        return phone;
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
        return true;
    }

    public String getId() {
        return id;
    }

    public ProfileRole getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }
}
