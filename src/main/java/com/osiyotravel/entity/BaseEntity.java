package com.osiyotravel.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected String id;

    @Column(name = "created_date")
    @CreationTimestamp
    protected LocalDateTime createdDate;


    @Column(name = "visible")
    protected Boolean visible = Boolean.TRUE;
}
