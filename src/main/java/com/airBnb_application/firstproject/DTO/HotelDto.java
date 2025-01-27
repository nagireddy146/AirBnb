package com.airBnb_application.firstproject.DTO;

import com.airBnb_application.firstproject.Entities.HotelContactInfo;
import lombok.*;


@Data
public class HotelDto {

    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo hotelContactInfo;
    private Boolean active;


}


