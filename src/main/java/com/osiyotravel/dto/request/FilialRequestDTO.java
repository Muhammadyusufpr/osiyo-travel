package com.osiyotravel.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilialRequestDTO {
    private String name;
    private String ownerId;
}
