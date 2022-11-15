package com.osiyotravel.dto.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class ProfileAuthDTO {

    @NotBlank(message = "Username not null!")
    private String username;

    @NotNull(message = "Password not null!")
    private String password;
}
