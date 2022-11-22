package com.osiyotravel.dto.request;

import com.osiyotravel.entity.FlightEntity;
import com.osiyotravel.enums.AirplaneType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class TicketRequestDTO {
    private String name;
    private List<FlightEntity> flightList;
    private String clientName;
    private LocalDateTime flightTime;
    private String airPlane;
    private String seat;
    private String fromCountry;
    private String toCountry;
    private String clientId;
    private AirplaneType airplaneType;
    private LocalDateTime endTime;
}
