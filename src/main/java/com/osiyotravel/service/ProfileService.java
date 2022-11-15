package com.osiyotravel.service;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ProfileCreateDTO;
import com.osiyotravel.dto.response.ProfileResDTO;
import com.osiyotravel.dto.update.ProfileUpdateDTO;
import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.enums.ProfileRole;
import com.osiyotravel.exception.ItemNotFoundException;
import com.osiyotravel.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;


    public ApiResponse<?> create(ProfileCreateDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());

        if (optional.isPresent()) {
            log.info("Username already exists!{}", dto.getUsername());
            return new ApiResponse<>("Username already exists!", 400, true);
        }

        ProfileEntity entity = new ProfileEntity();

        entity.setUsername(dto.getUsername());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(ProfileRole.ROLE_MANAGER);
        profileRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }

    public ProfileEntity get(String id) {
        return profileRepository
                .findByIdAndVisibleTrue(id).orElseThrow(() -> {
                    throw new ItemNotFoundException("Manager not found!");
                });
    }


    public ApiResponse<?> getById(String id) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);

        if (optional.isEmpty()) {
            log.info("Profile not found!", id);
            return new ApiResponse<>("Profile not found!", 400, true);
        }
        return new ApiResponse<>("Success!", 200, false, toDTO(optional.get()));
    }


    public ProfileResDTO toDTO(ProfileEntity entity) {
        ProfileResDTO dto = new ProfileResDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setRole(entity.getRole());
        dto.setPhone(entity.getPhone());
        dto.setUsername(entity.getUsername());
        return dto;
    }

    public ApiResponse<?> update(ProfileUpdateDTO dto, String id) {
        Optional<ProfileEntity> employee = profileRepository
                .findByUsernameAndVisibleTrue(dto.getUsername());

        if (employee.isPresent()) {
            log.warn("Username all ready Exists! username = {}", dto.getUsername());
            return new ApiResponse<>("Username all ready Exists!", 400, true);
        }


        ProfileEntity entity = get(id);

        if (entity == null) {
            log.info("Profile not found!");
            return new ApiResponse<>("Profile not found!", 400, true);
        }

        entity.setUsername(dto.getUsername());
        entity.setPhone(dto.getPhone());
        profileRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }





}
