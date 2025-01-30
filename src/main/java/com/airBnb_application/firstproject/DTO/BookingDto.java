package com.airBnb_application.firstproject.DTO;

import com.airBnb_application.firstproject.Entities.*;
import com.airBnb_application.firstproject.Entities.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {

    private Long id;
//    private Hotel hotel;
//    private Room room;
    private User user;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    private Integer rooms_count;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus booking_status;
    private Payment payment;
    private Set<GuestDto> guestDto;

}
