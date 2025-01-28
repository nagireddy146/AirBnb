package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.DTO.RoomDto;
import com.airBnb_application.firstproject.Entities.Hotel;
import com.airBnb_application.firstproject.Entities.Room;
import com.airBnb_application.firstproject.Exception.ResourceNotFoundException;
import com.airBnb_application.firstproject.Repositories.HotelRepository;
import com.airBnb_application.firstproject.Repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("creating a new room in hotel with ID {}", hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID"+hotelId));

        Room room= modelMapper.map(roomDto,Room.class);

        room.setHotel(hotel);
        room = roomRepository.save(room);
        //TODO: create inventory as soon as room is created and if hotel is Active
        if(hotel.getActive()){
            inventoryService.generateInventoryOfRoomFor1Year(room);
        }
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {

        log.info(" Getting all the rooms in hotel with Id{}",hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID"+hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long hotelID,Long roomId) {

        Hotel hotel = hotelRepository
                .findById(hotelID)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID"+hotelID));

        log.info(" Getting the room by room_id{}",roomId);
        Room room = roomRepository.findByIdAndHotelId(roomId,hotelID)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with ID"+roomId));

        return modelMapper.map(room,RoomDto.class);

    }

    @Override
    @Transactional
    public void deleteRoomById(Long hotelID,Long roomID) {


        Hotel hotel = hotelRepository
                .findById(hotelID)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID"+hotelID));

        log.info(" Trying to delete the room by Id{}",roomID);

        Room room = roomRepository.findByIdAndHotelId(roomID,hotelID)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with ID"+roomID));


        inventoryService.deleteFutureInventoryOfRoom(room);
        roomRepository.deleteById(roomID);
        //TODO: delete all the future inventories of the room

    }
}
