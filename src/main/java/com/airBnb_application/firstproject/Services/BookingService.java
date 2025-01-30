package com.airBnb_application.firstproject.Services;


import com.airBnb_application.firstproject.DTO.BookingDto;
import com.airBnb_application.firstproject.DTO.BookingObject;
import com.airBnb_application.firstproject.DTO.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initializeBooking(BookingObject bookingObject);

    BookingDto addGuestsToBooking(Long bookingId, List<GuestDto> guests);
}
