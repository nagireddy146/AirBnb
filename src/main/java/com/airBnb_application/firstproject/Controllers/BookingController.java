package com.airBnb_application.firstproject.Controllers;

import com.airBnb_application.firstproject.DTO.BookingDto;
import com.airBnb_application.firstproject.DTO.BookingObject;
import com.airBnb_application.firstproject.DTO.GuestDto;
import com.airBnb_application.firstproject.Services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDto> initializeBooking(@RequestBody BookingObject bookingObject){

        return ResponseEntity.ok(bookingService.initializeBooking(bookingObject));

    }

    @PostMapping("/{booking_id}/addGuests")
    public ResponseEntity<BookingDto> addGuestsToBooking(@PathVariable Long booking_id, @RequestBody List<GuestDto> guests){

        return ResponseEntity.ok(bookingService.addGuestsToBooking(booking_id,guests));
    }
}

