package com.osiyotravel.dto.response;

import com.osiyotravel.dto.request.FilialRequestDTO;
import com.osiyotravel.enums.FilialStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FilialResponseDTO extends FilialRequestDTO {
    private String id;
    private LocalDateTime createdDate;
    private FilialStatus status;
}
