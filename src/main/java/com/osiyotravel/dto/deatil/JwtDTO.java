package com.osiyotravel.dto.deatil;

import com.osiyotravel.enums.ProfileRole;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtDTO {

    private String id;

    private ProfileRole profileRole;

    private String username;

    private String phone;

}
