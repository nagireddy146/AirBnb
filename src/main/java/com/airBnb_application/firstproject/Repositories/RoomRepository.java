package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
