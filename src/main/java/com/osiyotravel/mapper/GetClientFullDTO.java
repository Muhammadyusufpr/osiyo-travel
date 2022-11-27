package com.osiyotravel.mapper;

import com.osiyotravel.enums.Gender;

public interface GetClientFullDTO {

    String getId();

    String getName();

    String getSurname();

    String getPhone();

    String getPrice();

    Gender getGender();

    String getPhotoId();

    String getFilialName();
}
