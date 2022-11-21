package com.osiyotravel.entity;

import com.osiyotravel.enums.AirplaneType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "ticket")
public class TicketEntity extends BaseEntity {
    @Column
    private String name;

    @Column
    private String flight; //рейс

    @Column
    private LocalDateTime flightTime;

    @Column
    private String airPlane;

    @Column
    private String seat;

    @Column
    private String from;

    @Column
    private String to;

    @Column(name = "client_id")
    private String clientId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;

    @Column
    @Enumerated(EnumType.STRING)
    private AirplaneType airplaneType;

    @Column
    private String clientName;


}
