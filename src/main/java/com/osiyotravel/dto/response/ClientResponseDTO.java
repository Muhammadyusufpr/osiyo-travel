package com.osiyotravel.dto.response;

import com.osiyotravel.dto.request.ClientRequestDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class ClientResponseDTO extends ClientRequestDTO {
    private String id;
    private LocalDateTime createdDate;
    private String filialId;
}
