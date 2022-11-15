package com.osiyotravel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "attach")
@Getter
@Setter
public class AttachEntity extends BaseEntity {
    @Column(name = "path")
    private String path;

    @Column(name = "extension")
    private String extension;

    @Column(name = "origen_name")
    private String origenName;

    @Column(name = "size")
    private Long size;
}
