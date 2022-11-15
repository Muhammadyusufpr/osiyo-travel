package com.osiyotravel.config.detail;


import com.osiyotravel.enums.ProfileRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class EntityDetails {

    private static CustomProfileDetails getEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomProfileDetails) authentication.getPrincipal();
    }

    public static String getUsernameEmployee() {
        return getEntity().getUsername();
    }

    public static String getPhoneProfile() {
        return getEntity().getPhone();
    }

    public static String getId() {
        return getEntity().getId();
    }

    public static ProfileRole getRole() {
        return getEntity().getRole();
    }

}
