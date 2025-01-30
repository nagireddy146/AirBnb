package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRespository  extends JpaRepository<Booking, Long> {
}
