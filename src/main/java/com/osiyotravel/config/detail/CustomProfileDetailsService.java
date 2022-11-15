package com.osiyotravel.config.detail;


import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.enums.ProfileRole;
import com.osiyotravel.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class CustomProfileDetailsService implements UserDetailsService {
    private final ProfileRepository employeeRepository;


    @Override
    public CustomProfileDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProfileEntity profileEntity = employeeRepository
                .findByUsernameAndVisibleTrue(username)
                .orElse(null);

        CustomProfileDetails details = null;

        if(profileEntity != null && profileEntity.getRole().equals(ProfileRole.ROLE_MANAGER)){
            details = new CustomProfileDetails
                    (
                            profileEntity.getId(),
                            profileEntity.getRole(),
                            profileEntity.getUsername(),
                            profileEntity.getPhone()
                    );
        }
        return  details;
    }
}
