package com.osiyotravel.dto.response;

import com.osiyotravel.enums.ProfileRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProfileResDTO {
    private String id;
    private String username;
    private String fullName;
    private String phone;
    private ProfileRole role;
    private LocalDateTime createdDate;
    private String accessToken;
}
