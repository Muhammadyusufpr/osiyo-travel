package com.osiyotravel.entity;

import com.osiyotravel.enums.FilialStatus;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "filial")
public class FilialEntity extends BaseEntity {
    @Column
    private String name;

    @Column(name = "owner_id")
    private String ownerId;
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private ProfileEntity owner;

    @Column
    @Enumerated(EnumType.STRING)
    private FilialStatus status;
}
