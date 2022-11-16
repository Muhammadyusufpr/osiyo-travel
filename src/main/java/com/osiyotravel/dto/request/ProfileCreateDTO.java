package com.osiyotravel.dto.request;

import com.osiyotravel.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ProfileCreateDTO {

    @NotBlank(message = "Username is required!")
    @Length(min = 2,message = "Username size min 3 !")
    private String username;

    @NotBlank(message = "Password is required!")
    @Length(min = 4,message = "Username size min 3 !")
    private String password;

    @NotBlank(message = "Contact is required!")
    private String phone;

    @NotNull(message = "full name not be null")
    private String fullName;

    private ProfileRole role;

}
