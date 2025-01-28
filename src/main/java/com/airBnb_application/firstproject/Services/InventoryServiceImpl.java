package com.airBnb_application.firstproject.Services;


import com.airBnb_application.firstproject.Entities.Inventory;
import com.airBnb_application.firstproject.Entities.Room;
import com.airBnb_application.firstproject.Repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

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

        LocalDate date = LocalDate.now();

        inventoryRepository.deleteByDateAfterAndRoom(date,room);
    }
}
