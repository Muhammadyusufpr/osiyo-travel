package com.osiyotravel.service;

import com.osiyotravel.config.detail.EntityDetails;
import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ProfileCreateDTO;
import com.osiyotravel.dto.request.ProfileDTOForSuperAdmin;
import com.osiyotravel.dto.response.ProfileCountDTO;
import com.osiyotravel.dto.response.ProfileResDTO;
import com.osiyotravel.dto.update.ProfileUpdateDTO;
import com.osiyotravel.entity.FilialEntity;
import com.osiyotravel.entity.ProfileEntity;
import com.osiyotravel.enums.ProfileRole;
import com.osiyotravel.exception.ItemNotFoundException;
import com.osiyotravel.repository.ProfileRepository;
import com.osiyotravel.util.MD5Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;
    private final FilialService filialService;


    public ApiResponse<?> create(ProfileCreateDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());

        if (optional.isPresent()) {
            log.info("Username already exists!{}", dto.getUsername());
            return new ApiResponse<>("Username already exists!", 400, true);
        }

        ProfileEntity entity = new ProfileEntity();

        entity.setUsername(dto.getUsername());
        entity.setPhone(dto.getPhone());
        if (EntityDetails.getRole().equals(ProfileRole.ROLE_SUPER_ADMIN)) {
            entity.setFilialId(dto.getFilialId());
        } else {
            entity.setFilialId(EntityDetails.getFilialId());
        }
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setRole(dto.getRole());
        entity.setFullName(dto.getFullName());
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
            log.info("Profile not found!{}", id);
            return new ApiResponse<>("Profile not found!", 400, true);
        }
        return new ApiResponse<>("Success!", 200, false, toDTO(optional.get()));
    }

    public ApiResponse<?> getCurrentProfile() {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(EntityDetails.getId());

        if (optional.isEmpty()) {
            log.info("Profile not found!{}", EntityDetails.getId());
            return new ApiResponse<>("Profile not found!", 400, true);
        }
        return new ApiResponse<>("Success!", 200, false, toDTO(optional.get()));
    }


    public ProfileResDTO toDTO(ProfileEntity entity) {
        ProfileResDTO dto = new ProfileResDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setRole(entity.getRole());
        dto.setFullName(entity.getFullName());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());
        if (entity.getFilial() != null) dto.setFilialName(entity.getFilial().getName());

        return dto;
    }

    public ApiResponse<?> update(ProfileUpdateDTO dto, String id) {
        ProfileEntity entity = get(id);
        Optional<ProfileEntity> employee = profileRepository
                .findByUsernameAndVisibleTrue(dto.getUsername());

        if (employee.isPresent() && !entity.getUsername().equals(employee.get().getUsername())) {
            log.warn("Username all ready Exists! username = {}", dto.getUsername());
            return new ApiResponse<>("Username all ready Exists!", 400, true);
        }

        if (Optional.ofNullable(dto.getFilialId()).isPresent())
            entity.setFilialId(dto.getFilialId());


        if (Optional.ofNullable(dto.getUsername()).isPresent())
            entity.setUsername(dto.getUsername());

        if (Optional.ofNullable(dto.getPhone()).isPresent())
            entity.setPhone(dto.getPhone());

        if (Optional.ofNullable(dto.getFullName()).isPresent())
            entity.setFullName(dto.getFullName());

        if (Optional.ofNullable(dto.getPassword()).isPresent())
            entity.setPassword(MD5Util.getMd5(dto.getPassword()));

        profileRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }

    public ApiResponse<?> getCountProfile() {
        List<ProfileEntity> list = profileRepository.findAllByVisibleTrue();
        Integer adminCount = 0;
        Integer managerCount = 0;
        ProfileCountDTO dto = new ProfileCountDTO();

        for (ProfileEntity entity : list) {
            if (entity.getRole().equals(ProfileRole.ROLE_ADMIN)) {
                adminCount++;
            } else if (entity.getRole().equals(ProfileRole.ROLE_MANAGER)) {
                managerCount++;
            }
        }
        Integer count = clientService.getCount();
        dto.setManagerCount(managerCount);
        dto.setAdminCount(adminCount);
        dto.setClientCount(count);
        return new ApiResponse<>("Success!", 200, false, dto);
    }


    public Boolean delete(String id) {
        ProfileEntity entity = get(id);
        profileRepository.updateVisibleAndDeletedDate(id, false, LocalDateTime.now());
        return true;
    }


    public ApiResponse<List<ProfileResDTO>> getAll(ProfileDTOForSuperAdmin dto) {
        List<ProfileResDTO> list = null;
        if (EntityDetails.getRole().equals(ProfileRole.ROLE_SUPER_ADMIN)) {
            list = profileRepository.findByRoleAndVisibleTrue(dto.getRole()).stream().map(this::toDTO).toList();
        } else {
            list = profileRepository.findByFilialIdAndRoleAndVisibleTrue(
                    EntityDetails.getFilialId(), dto.getRole()).stream().map(this::toDTO).toList();
        }
        return new ApiResponse<>("Success!", 200, false, list);
    }

}
