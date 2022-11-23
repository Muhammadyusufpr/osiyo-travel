package com.osiyotravel.mapper;

import com.osiyotravel.dto.ClientAttachDTO;
import com.osiyotravel.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ClientMapperDTO {
    private String id;
    private String name;
    private String surname;
    private String phone;
    private Gender gender;
    private String attachId;
    private String filialId;
    private String price;
    private ClientAttachDTO attachDTO;

    private List<ClientMapperDTO> list;
    private Long totalElement;

    public ClientMapperDTO(List<ClientMapperDTO> list, Long totalElement) {
        this.list = list;
        this.totalElement = totalElement;
    }

    public ClientMapperDTO(String id, String name, String surname, String phone, Gender gender, String attachId, String filialId, String price) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.gender = gender;
        this.attachId = attachId;
        this.filialId = filialId;
        this.price = price;
    }
}
