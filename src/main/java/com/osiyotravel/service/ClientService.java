package com.osiyotravel.service;

import com.osiyotravel.config.detail.EntityDetails;
import com.osiyotravel.dto.ClientAttachDTO;
import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.ClientFilterDTO;
import com.osiyotravel.dto.request.ClientRequestDTO;
import com.osiyotravel.dto.response.ClientPaginationDTO;
import com.osiyotravel.dto.response.ClientResponseDTO;
import com.osiyotravel.entity.ClientEntity;
import com.osiyotravel.exception.ItemNotFoundException;
import com.osiyotravel.mapper.ClientMapperDTO;
import com.osiyotravel.repository.ClientRepository;
import com.osiyotravel.repository.filter.ClientFilterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientFilterRepository clientFilterRepository;
    private final AttachService attachService;
    @Autowired
    @Lazy
    private TicketService ticketService;

    public ApiResponse<?> create(ClientRequestDTO dto) {
        ClientEntity entity = new ClientEntity();

        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setGender(dto.getGender());
        entity.setAttachId(dto.getAttachId());
        entity.setPrice(dto.getPrice());
        entity.setFilialId(EntityDetails.getFilialId());
        clientRepository.save(entity);
        ticketService.create(dto.getTicket(), entity.getId(), entity.getName());
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
        entity.setFilialId(EntityDetails.getFilialId());
        clientRepository.save(entity);
        ticketService.update(dto.getTicket().getId(), dto.getTicket(), entity.getId(), entity.getName());
        return new ApiResponse<>("Success!", 200, false);
    }

    public ApiResponse<Boolean> delete(String id) {
        ClientEntity entity = get(id);
        clientRepository.updateVisibleAndDeletedDate(id, false, LocalDateTime.now());
        ticketService.delete(id);
        return new ApiResponse<>("Success!", 200, false);
    }

    public ApiResponse<List<ClientResponseDTO>> getAll() {
        List<ClientResponseDTO> list = clientRepository.findAllByFilialIdAndVisibleTrue(EntityDetails.getFilialId()).stream().map(this::toDTO).toList();
        return new ApiResponse<>("Success!", 200, false, list);
    }

    public Integer getCount() {
        Optional<Integer> count = clientRepository.getCount();

        if (count.isEmpty()) {
            log.info("Client count not found!");
            return null;
        }
        return count.get();
    }

    public ApiResponse<ClientPaginationDTO> filter(ClientFilterDTO dto, int page, int size) {
        ClientMapperDTO filter = clientFilterRepository.filter(dto, page, size);
        List<ClientMapperDTO> list = filter.getList();

        for (ClientMapperDTO mapperDTO : list) {
            mapperDTO.setAttachDTO(new ClientAttachDTO(mapperDTO.getAttachId(),
                    attachService.toOpenURL(mapperDTO.getAttachId())));
        }
        PageImpl<ClientMapperDTO> dtos = new PageImpl<>(list, PageRequest.of(page, size), filter.getTotalElement());
        return new ApiResponse<>("Success!", 200, false,
                new ClientPaginationDTO(list, dtos.getTotalPages(), filter.getTotalElement()));

    }
}
