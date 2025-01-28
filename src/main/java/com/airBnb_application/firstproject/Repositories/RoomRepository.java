package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByIdAndHotelId(Long roomId, Long hotelId);
}
