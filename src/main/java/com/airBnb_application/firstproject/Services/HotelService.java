package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.Entities.Hotel;
import org.springframework.stereotype.Service;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long id);

}
