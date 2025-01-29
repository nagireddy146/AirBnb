package com.airBnb_application.firstproject.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchDto {
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfRooms;

    private Integer page=0;
    private Integer size=4;
}
