package com.osiyotravel.config.detail;

import com.osiyotravel.dto.deatil.CurrentFilial;
import com.osiyotravel.entity.FilialEntity;
import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomProfileDetails implements UserDetails {

    private String id;

    private ProfileRole role;

    private String username;

    private String phone;

    private FilialEntity filial;

    public CustomProfileDetails(String id, ProfileRole role, String username, String phone,FilialEntity filial) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.phone = phone;
        this.filial = filial;
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

    public FilialEntity getFilial() {
        return filial;
    }
}
