package com.osiyotravel.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.osiyotravel.dto.request.TicketRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketResponseDTO extends TicketRequestDTO {
    private String id;
    private LocalDateTime createdDate;
    private String clientId;
    private String clientName;
}
