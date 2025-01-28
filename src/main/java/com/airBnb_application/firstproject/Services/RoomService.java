package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.DTO.RoomDto;
import com.airBnb_application.firstproject.Entities.Room;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(Long hotelId, RoomDto roomDto);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);

    RoomDto getRoomById(Long hotelId,Long roomId);

    void deleteRoomById(Long hotelId, Long roomID);
}
