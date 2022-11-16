package com.osiyotravel.dto.request;

import com.osiyotravel.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileDTOForSuperAdmin {
    private ProfileRole role;
}
