package com.osiyotravel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "flight")
public class FlightEntity extends BaseEntity {
    @Column
    private String name;

}
