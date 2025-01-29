package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Hotel;
import com.airBnb_application.firstproject.Entities.Inventory;
import com.airBnb_application.firstproject.Entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByRoom(Room room);

    @Query(
            """
            SELECT DISTINCT i.hotel
            FROM Inventory i
            WHERE i.city = :city
                AND i.date BETWEEN :startDate AND :endDate
                AND i.closed = false
                AND (i.totalCount-i.bookedCount) >= :numberOfRooms
            GROUP BY i.hotel,i.room
            HAVING COUNT(i.date) = :dateCount                \s
                   \s
                   \s
           \s"""
    )
    Page<Hotel> findHotelWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("numberOfRooms") Integer numberOfRooms,
            @Param("dateCount") Long dateCount,
            Pageable pageable


    );
}
