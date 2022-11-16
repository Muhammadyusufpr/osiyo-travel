package com.osiyotravel.entity;

import com.osiyotravel.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "client")
public class ClientEntity extends BaseEntity {
    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(name = "attach_id")
    private String attachId;
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;


}
