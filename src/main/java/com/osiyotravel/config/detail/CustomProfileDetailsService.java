package com.osiyotravel.config.detail;


import com.osiyotravel.dto.deatil.CurrentFilial;
import com.osiyotravel.entity.FilialEntity;
import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;


@Component
@Slf4j
@RequiredArgsConstructor
public class CustomProfileDetailsService implements UserDetailsService {
    private final ProfileRepository employeeRepository;

    private Map<String, CurrentFilial> filialMap = new LinkedHashMap<>();


    @Override
    public CustomProfileDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProfileEntity profileEntity = employeeRepository
                .findByUsernameAndVisibleTrue(username)
                .orElse(null);

        CustomProfileDetails details = null;

        if(profileEntity != null){
            FilialEntity filial = new FilialEntity();
            if (filialMap.containsKey(username)) {
                filial = filialMap.get(username).getFilial();
            }
            details = new CustomProfileDetails
                    (
                            profileEntity.getId(),
                            profileEntity.getRole(),
                            profileEntity.getUsername(),
                            profileEntity.getPhone(),
                            filial
                    );
        }
        assert details != null;
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(details,
                null, details.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return  details;
    }


    public Map<String, CurrentFilial> getFilialMap() {
        return filialMap;
    }
}
