package com.osiyotravel.entity;

import com.osiyotravel.enums.ProfileRole;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {
    @Column
    private String username;

    @Column
    private String fullName;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column
    private String phone;

    @Column(name = "filial_id")
    private String filialId;
    @JoinColumn(name = "filial_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private FilialEntity filial;



}
