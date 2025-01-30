package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
