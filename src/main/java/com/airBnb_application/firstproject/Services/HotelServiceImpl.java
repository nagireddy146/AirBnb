package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.Entities.Hotel;
import com.airBnb_application.firstproject.Entities.Room;
import com.airBnb_application.firstproject.Entities.User;
import com.airBnb_application.firstproject.Exception.ResourceNotFoundException;
import com.airBnb_application.firstproject.Exception.UnautorizedException;
import com.airBnb_application.firstproject.Repositories.HotelRepository;
import com.airBnb_application.firstproject.Repositories.InventoryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.BooleanNode;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Getter
@Setter
@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{
    private final InventoryRepository inventoryRepository;

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomService roomService;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info(" Creating a hotel with name {}",hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel.setOwner(user);

        hotel = hotelRepository.save(hotel);
        log.info("created a user with the details{}",hotel.getId());

        return modelMapper.map(hotel, HotelDto.class);

        }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info(" Getting the hotel with Id {} ",id);
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id "   +id));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw  new UnautorizedException("User is not Owner");
        }
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info(" Updating the hotel with Id {} ",id);
        Hotel hotel = hotelRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id "   +id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw  new UnautorizedException("User is not Owner");
        }
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id "   +id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw  new UnautorizedException("User is not Owner");
        }
        for(Room room : hotel.getRooms()){
            inventoryService.deleteFutureInventoryOfRoom(room);
            roomService.deleteRoomById(hotel.getId(), room.getId());
        }

        hotelRepository.deleteById(id);
        //TODO : Delete the future inventories for this ID

    }

    @Override
    public void activateHotel(Long hotel_id) {
        log.info("Activating the hotel with id {}", hotel_id);
        Hotel hotel= hotelRepository
                .findById(hotel_id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID+hotel_id") );

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw  new UnautorizedException("User is not Owner");
        }
        hotel.setActive(true);

        //  TODO: Create inventory for all the rooms in Hotel

        for(Room room: hotel.getRooms()){
            inventoryService.generateInventoryOfRoomFor1Year(room);
        }

    }
}
