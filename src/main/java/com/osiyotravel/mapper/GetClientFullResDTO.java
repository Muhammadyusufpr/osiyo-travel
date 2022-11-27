package com.osiyotravel.mapper;

import com.osiyotravel.dto.AttachDTO;
import com.osiyotravel.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetClientFullResDTO {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private Gender gender;
    private AttachDTO attachDTO;
    private String filialName;
    private String price;
}
