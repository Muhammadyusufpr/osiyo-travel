package com.osiyotravel.service;

import com.osiyotravel.config.detail.EntityDetails;
import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ClientRequestDTO;
import com.osiyotravel.dto.response.ClientResponseDTO;
import com.osiyotravel.entity.ClientEntity;
import com.osiyotravel.exception.ItemNotFoundException;
import com.osiyotravel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ApiResponse<?> create(ClientRequestDTO dto) {

        ClientEntity entity = new ClientEntity();

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setAttachId(dto.getAttachId());
        entity.setPrice(dto.getPrice());
        entity.setFlight(dto.getFlight());
        entity.setAirPlane(dto.getAirPlane());
        entity.setFilialId(EntityDetails.getFilialId());
        clientRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }

    public ClientEntity get(String id) {
        return clientRepository
                .findByIdAndVisibleTrue(id).orElseThrow(() -> {
                    throw new ItemNotFoundException("Client not found!");
                });
    }

    public ClientResponseDTO toDTO(ClientEntity entity) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setName(entity.getName());
        dto.setFilialId(entity.getFilialId());
        dto.setSurname(entity.getSurname());
        dto.setPhone(entity.getPhone());
        dto.setGender(entity.getGender());
        dto.setAttachId(entity.getAttachId());
        dto.setPrice(entity.getPrice());
        dto.setFlight(entity.getFlight());
        dto.setAirPlane(entity.getAirPlane());
        return dto;
    }

    public ApiResponse<?> getById(String id) {
        Optional<ClientEntity> optional = clientRepository.findByIdAndVisibleTrue(id);

        if (optional.isEmpty()) {
            log.info("Client not found!{}", id);
            return new ApiResponse<>("Client not found!", 400, true);
        }
        return new ApiResponse<>("Success!", 200, false, toDTO(optional.get()));
    }

    public ApiResponse<?> update(String id, ClientRequestDTO dto) {
        ClientEntity entity = get(id);

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setAttachId(dto.getAttachId());
        entity.setPrice(dto.getPrice());
        entity.setFlight(dto.getFlight());
        entity.setAirPlane(dto.getAirPlane());
        entity.setFilialId(EntityDetails.getFilialId());
        clientRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }

    public ApiResponse<Boolean> delete(String id) {
        ClientEntity entity = get(id);
        clientRepository.updateVisibleAndDeletedDate(id, false, LocalDateTime.now());
        return new ApiResponse<>("Success!", 200, false);
    }

    public ApiResponse<List<ClientResponseDTO>> getAll() {
        List<ClientResponseDTO> list = clientRepository.findAllByFilialIdAndVisibleTrue(EntityDetails.getFilialId()).stream().map(this::toDTO).toList();
        return new ApiResponse<>("Success!", 200, false, list);
    }


}
