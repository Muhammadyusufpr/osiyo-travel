package com.osiyotravel.entity;

import com.osiyotravel.enums.AirplaneType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "ticket")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class TicketEntity extends BaseEntity {
    @Column
    private String name;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<FlightEntity> flightEntityList;

    @Column
    private LocalDateTime flightTime;

    @Column
    private String airPlane;

    @Column
    private String seat;

    @Column
    private String fromCountry;

    @Column
    private String toCountry;

    @Column(name = "client_id")
    private String clientId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;

    @Column
    @Enumerated(EnumType.STRING)
    private AirplaneType airplaneType;

    @Column
    private String clientName; // client qaytish vaqtiga 14 -15 boshka type boladi

    @Column
    private LocalDateTime endTime;


}
