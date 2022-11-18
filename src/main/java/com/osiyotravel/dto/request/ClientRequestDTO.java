package com.osiyotravel.dto.request;

import com.osiyotravel.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientRequestDTO {
    private String name;
    private String surname;
    private String phone;
    private Gender gender;
    private String attachId;
    private String price;
    private String flight; //рейс
    private String airPlane;
}
