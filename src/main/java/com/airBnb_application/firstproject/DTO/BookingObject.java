package com.airBnb_application.firstproject.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingObject {
    private Long hotel_id;
    private Long room_id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfRooms;
}
