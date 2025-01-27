package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
