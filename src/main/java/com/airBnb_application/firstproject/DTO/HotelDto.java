package com.airBnb_application.firstproject.DTO;

import com.airBnb_application.firstproject.Entities.HotelContactInfo;
import com.airBnb_application.firstproject.Entities.Room;
import lombok.*;

import java.util.List;


@Data
public class HotelDto {

    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo hotelContactInfo;
    private Boolean active;
    private List<Room> rooms;


}


