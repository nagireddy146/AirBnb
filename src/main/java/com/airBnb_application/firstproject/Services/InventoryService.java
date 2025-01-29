package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.DTO.HotelSearchDto;
import com.airBnb_application.firstproject.Entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

public interface InventoryService {

     void generateInventoryOfRoomFor1Year(Room room);

     void deleteFutureInventoryOfRoom(Room room);

     Page<HotelDto> searchHotels(HotelSearchDto hotelSearchDto);
}
