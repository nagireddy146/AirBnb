package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Inventory;
import com.airBnb_application.firstproject.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
