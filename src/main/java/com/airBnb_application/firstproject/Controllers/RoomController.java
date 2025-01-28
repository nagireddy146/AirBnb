package com.airBnb_application.firstproject.Controllers;

import com.airBnb_application.firstproject.DTO.RoomDto;
import com.airBnb_application.firstproject.Entities.Room;
import com.airBnb_application.firstproject.Services.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotel_id}/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotel_id,@RequestBody RoomDto roomDto){

        log.info(" Trying to create a new room in hotel with id{}",hotel_id);

        roomDto= roomService.createNewRoom(hotel_id,roomDto);

        return new ResponseEntity<>(roomDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable Long hotel_id){

        log.info(" Getting all the rooms in the given hotel");

        List<RoomDto> rooms = roomService.getAllRoomsInHotel(hotel_id);

        return ResponseEntity.ok(rooms);

    }
    @GetMapping("/{room_id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long hotel_id, @PathVariable Long room_id){

        log.info(" Getting the room in the given hotel");

        RoomDto roomDto = roomService.getRoomById(hotel_id,room_id);

        return ResponseEntity.ok(roomDto);

    }


    @DeleteMapping("/{room_id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long hotel_id, @PathVariable Long room_id){

        log.info(" Deleting the room with id{}",room_id);

          roomService.deleteRoomById(hotel_id,room_id);

          return ResponseEntity.noContent().build();

    }

}
