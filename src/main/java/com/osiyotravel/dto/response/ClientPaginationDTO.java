package com.osiyotravel.dto.response;

import com.osiyotravel.mapper.ClientMapperDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientPaginationDTO {
    private List<ClientMapperDTO> dtoList;
    private int totalPages;
    private Long totalSize;
}
