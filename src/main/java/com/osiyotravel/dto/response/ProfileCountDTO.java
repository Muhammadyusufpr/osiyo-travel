package com.osiyotravel.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileCountDTO {
    private Integer adminCount;
    private Integer managerCount;
    private Integer clientCount;
}
