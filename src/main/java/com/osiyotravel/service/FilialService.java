package com.osiyotravel.service;

import com.osiyotravel.config.detail.EntityDetails;
import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.FilialRequestDTO;
import com.osiyotravel.dto.response.FilialResponseDTO;
import com.osiyotravel.entity.FilialEntity;
import com.osiyotravel.enums.FilialStatus;
import com.osiyotravel.exception.ItemNotFoundException;
import com.osiyotravel.repository.FilialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilialService {
    private final FilialRepository filialRepository;


    public ApiResponse<?> create(FilialRequestDTO dto) {
        Optional<FilialEntity> optional = filialRepository.findByNameAndDeletedDateIsNull(dto.getName());
        if (optional.isPresent()) {
            log.info("Filial already exists!{}", dto.getName());
            return new ApiResponse<>("Filial already exists!", 400, true);
        }

        FilialEntity entity = new FilialEntity();

        entity.setName(dto.getName());
        entity.setOwnerId(EntityDetails.getId());
        entity.setStatus(FilialStatus.ACTIVE);
        filialRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }

    public FilialEntity get(String id) {
        return filialRepository.findByIdAndVisibleTrue(id).orElse(null);
    }


    public ApiResponse<?> getById(String id) {
        Optional<FilialEntity> optional = filialRepository.findByIdAndVisibleTrue(id);

        if (optional.isEmpty()) {
            log.info("Filial not found!{}", id);
            return new ApiResponse<>("Profile not found!", 400, true);
        }
        return new ApiResponse<>("Success!", 200, false, toDTO(optional.get()));
    }


    public FilialResponseDTO toDTO(FilialEntity entity) {
        FilialResponseDTO dto = new FilialResponseDTO();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public ApiResponse<?> update(String id, FilialRequestDTO dto) {
        Optional<FilialEntity> optional = filialRepository.findByIdAndVisibleTrue(id);

        if (optional.isEmpty()) {
            log.info("Filial not found!{}", id);
            return new ApiResponse<>("Filial not found!", 400, true);
        }

        FilialEntity entity = optional.get();

        entity.setName(dto.getName());
        entity.setOwnerId(EntityDetails.getId());
        filialRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }

    public Boolean delete(String id) {
        FilialEntity entity = get(id);

        if (entity == null) {
            log.info("Filial not found!{}", id);
            return false;
        }
        filialRepository.updateVisibleAndDeletedDate(id, false, LocalDateTime.now());
        return true;
    }


    public ApiResponse<List<FilialResponseDTO>> getAll() {
        List<FilialResponseDTO> list = filialRepository.findAllByOwnerId(EntityDetails.getId()).stream().map(this::toDTO).toList();
        return new ApiResponse<>("Success!", 200, false, list);
    }


}
