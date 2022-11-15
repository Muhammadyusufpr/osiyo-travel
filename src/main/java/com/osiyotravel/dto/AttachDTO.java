package com.osiyotravel.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttachDTO {
    private String id;
    private String url;
    private String origenName;
    private LocalDateTime createdDate;
    private String path;

    public AttachDTO() {
    }
    public AttachDTO(String id, String url) {
        this.id = id;
        this.url = url;
    }


}
