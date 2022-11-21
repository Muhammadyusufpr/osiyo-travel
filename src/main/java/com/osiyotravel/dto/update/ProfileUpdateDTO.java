package com.osiyotravel.dto.update;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ProfileUpdateDTO {

    @NotBlank(message = "Username is required!")
    @Length(min = 2, message = "Username size min 3 !")
    private String username;

    @NotBlank(message = "Phone is required!")
    private String phone;

    @NotNull(message = "filialId name not be null")
    private String filialId;

    @NotNull(message = "full name name not be null")
    private String fullName;
}
