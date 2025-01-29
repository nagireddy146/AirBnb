package com.airBnb_application.firstproject.Services;


import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.DTO.HotelSearchDto;
import com.airBnb_application.firstproject.Entities.Hotel;
import com.airBnb_application.firstproject.Entities.Inventory;
import com.airBnb_application.firstproject.Entities.Room;
import com.airBnb_application.firstproject.Repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    private final ModelMapper modelMapper;

    private final InventoryRepository inventoryRepository;

    @Override
    public void generateInventoryOfRoomFor1Year(Room room) {

        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);

        for(;today.isBefore(endDate);today=today.plusDays(1)){

            Inventory inventory= Inventory
                    .builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(BigDecimal.valueOf(room.getBasePrice()))
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }


    }

    @Transactional
    @Override
    public void deleteFutureInventoryOfRoom(Room room) {

        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchDto hotelSearchDto) {
        Pageable pageable= PageRequest.of(hotelSearchDto.getPage(), hotelSearchDto.getSize());
        Long date_count =
                ChronoUnit.DAYS.between(hotelSearchDto.getStartDate(),hotelSearchDto.getEndDate())+1;
        Page<Hotel> hotels= inventoryRepository.findHotelWithAvailableInventory(hotelSearchDto.getCity(),
                hotelSearchDto.getStartDate(),
                hotelSearchDto.getEndDate(),
                hotelSearchDto.getNumberOfRooms(),
                date_count,
                pageable
        );

        return hotels.map((element) -> modelMapper.map(element, HotelDto.class));
    }
}
