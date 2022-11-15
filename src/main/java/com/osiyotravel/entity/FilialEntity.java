package com.osiyotravel.entity;

import com.osiyotravel.enums.FilialStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "filial")
public class FilialEntity extends BaseEntity {
    @Column
    private String name;

    @Column(name = "owner_id")
    private String ownerId;
    @JoinColumn(name = "owner_id", insertable = false, unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private ProfileEntity owner;

    @Column
    @Enumerated(EnumType.STRING)
    private FilialStatus status;
}
