package com.osiyotravel.dto.request;

import com.osiyotravel.enums.AirplaneType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TicketRequestDTO {
    private String name;
    private String clientName;
    private String flight; //рейс
    private LocalDateTime flightTime;
    private String airPlane;
    private String seat;
    private String from;
    private String to;
    private String clientId;
    private AirplaneType airplaneType;
}
