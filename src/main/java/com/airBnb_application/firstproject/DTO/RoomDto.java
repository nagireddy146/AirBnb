package com.airBnb_application.firstproject.DTO;


import lombok.Data;


@Data
public class RoomDto {
    private Long id;
    private String type;
    private Long basePrice;
    private String[] photos;
    private String amenities;
    private Integer totalCount;
    private Integer capacity;
}
