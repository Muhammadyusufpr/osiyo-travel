package com.osiyotravel.entity;

import com.osiyotravel.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {
    @Column
    private String username;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column
    private String phone;

}
