package com.osiyotravel.service;

import com.osiyotravel.config.detail.CustomProfileDetailsService;
import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.deatil.CurrentFilial;
import com.osiyotravel.dto.deatil.JwtDTO;
import com.osiyotravel.dto.request.ProfileAuthDTO;
import com.osiyotravel.dto.response.ProfileResDTO;
import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.repository.ProfileRepository;
import com.osiyotravel.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository employeeRepository;
    private final CustomProfileDetailsService customProfileDetailsService;


    public ApiResponse<ProfileResDTO> login(ProfileAuthDTO dto) {

        Optional<ProfileEntity> optional = employeeRepository.
                findByUsernameAndVisibleTrue(dto.getUsername());

        if (optional.isEmpty()) {
            log.warn("Employee not found! username = {}", dto.getUsername());
            return new ApiResponse<>("Profile not found", 400, true);
        }

        ProfileEntity profileEntity = optional.get();


        if (!passwordEncoder.matches(dto.getPassword(), profileEntity.getPassword())) {
            log.warn("Phone number Or Password wrong! username = {}", dto.getPassword());
            return new ApiResponse<>("Phone number Or Password wrong!", 400, true);
        }

        Map<String, CurrentFilial> filialMap = customProfileDetailsService.getFilialMap();
        CurrentFilial currentFilial = new CurrentFilial();
        currentFilial.setFilial(profileEntity.getFilial());
        filialMap.put(dto.getUsername(),currentFilial);
        customProfileDetailsService.loadUserByUsername(dto.getUsername());

        ProfileResDTO profile = new ProfileResDTO();
        profile.setUsername(profileEntity.getUsername());
        profile.setPhone(profileEntity.getPhone());
        profile.setCreatedDate(profileEntity.getCreatedDate());
        profile.setRole(profileEntity.getRole());

        JwtDTO jwtDTO = JwtDTO.builder()
                .id(profileEntity.getId())
                .phone(profileEntity.getUsername())
                .profileRole(profileEntity.getRole())
                .username(profileEntity.getUsername()).build();
        profile.setAccessToken(JwtUtil.encode(jwtDTO,30));

        return new ApiResponse<>("Success!", 200, false, profile);
    }

}
