package com.osiyotravel.service;

import com.osiyotravel.dto.deatil.ApiResponse;
import com.osiyotravel.dto.request.TicketRequestDTO;
import com.osiyotravel.dto.response.TicketResponseDTO;
import com.osiyotravel.entity.ClientEntity;
import com.osiyotravel.entity.TicketEntity;
import com.osiyotravel.exception.ItemNotFoundException;
import com.osiyotravel.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ClientService clientService;


    public ApiResponse<?> create(TicketRequestDTO dto, String clientId, String clientName) {
        ClientEntity client = clientService.get(clientId);

        TicketEntity entity = new TicketEntity();

        entity.setName(dto.getName());
        entity.setFlightEntityList(dto.getFlightList());
        entity.setFlightTime(dto.getFlightTime());
        entity.setAirPlane(dto.getAirPlane());
        entity.setSeat(dto.getSeat());
        entity.setFromCountry(dto.getFromCountry());
        entity.setToCountry(dto.getToCountry());
        entity.setClientId(clientId);
        entity.setClientName(clientName);
        entity.setAirplaneType(dto.getAirplaneType());
        entity.setEndTime(dto.getEndTime());
        entity.setCreatedDate(LocalDateTime.now());
        ticketRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }

    public TicketEntity get(String id) {
        return ticketRepository
                .findByIdAndVisibleTrue(id).orElseThrow(() -> {
                    throw new ItemNotFoundException("Ticket not found!");
                });
    }

    public ApiResponse<TicketResponseDTO> getById(String id) {
        Optional<TicketEntity> optional = ticketRepository.findByIdAndVisibleTrue(id);

        if (optional.isEmpty()) {
            log.info("Ticket not found!{}", id);
            return new ApiResponse<>("Ticket not found!", 400, true);
        }
        return new ApiResponse<>("Success!", 200, false, toDTO(optional.get()));
    }

    public ApiResponse<TicketResponseDTO> getByClientId(String clientId) {
        Optional<TicketEntity> optional = ticketRepository.findByClientIdAndVisibleTrue(clientId);

        if (optional.isEmpty()) {
            log.info("Client not found!{}", clientId);
            return new ApiResponse<>("Client not found!", 400, true);
        }
        return new ApiResponse<>("Success!", 200, false, toDTO(optional.get()));
    }

    public TicketResponseDTO toDTO(TicketEntity entity) {
        TicketResponseDTO dto = new TicketResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFlightTime(entity.getFlightTime());
        dto.setAirPlane(entity.getAirPlane());
        dto.setSeat(entity.getSeat());
        dto.setFlightList(entity.getFlightEntityList());
        dto.setFromCountry(entity.getFromCountry());
        dto.setToCountry(entity.getToCountry());
        dto.setClientId(entity.getClientId());
        dto.setClientName(entity.getClientName());
        dto.setAirplaneType(entity.getAirplaneType());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setEndTime(entity.getEndTime());
        return dto;
    }


    public ApiResponse<?> update(String id, TicketRequestDTO dto, String clientId, String clientName) {
        ClientEntity client = clientService.get(clientId);
        TicketEntity entity = get(id);

        entity.setName(dto.getName());
        entity.setFlightTime(dto.getFlightTime());
        entity.setAirPlane(dto.getAirPlane());
        entity.setSeat(dto.getSeat());
        entity.setFromCountry(dto.getFromCountry());
        entity.setToCountry(dto.getToCountry());
        entity.setClientId(clientId);
        entity.setClientName(clientName);
        entity.setFlightEntityList(dto.getFlightList());
        entity.setAirplaneType(dto.getAirplaneType());
        entity.setEndTime(dto.getEndTime());
        ticketRepository.save(entity);
        return new ApiResponse<>("Success!", 200, false);
    }


    public ApiResponse<?> delete(String id) {
        ticketRepository.updateVisibleAndDeletedDate(id, false, LocalDateTime.now());
        return new ApiResponse<>("Success!", 200, false);
    }

    public ApiResponse<List<TicketResponseDTO>> getAll() {
        List<TicketResponseDTO> list = ticketRepository.
                findAllByVisibleTrue().stream().map(this::toDTO).toList();
        return new ApiResponse<>("Success!", 200, false, list);
    }


}
