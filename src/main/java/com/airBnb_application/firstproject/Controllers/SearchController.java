package com.airBnb_application.firstproject.Controllers;

import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.DTO.HotelSearchDto;
import com.airBnb_application.firstproject.Entities.Hotel;
import com.airBnb_application.firstproject.Repositories.HotelRepository;
import com.airBnb_application.firstproject.Services.HotelService;
import com.airBnb_application.firstproject.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class SearchController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchForHotel(@RequestBody HotelSearchDto hotelSearchDto){

        Page<HotelDto> pages=inventoryService.searchHotels(hotelSearchDto);

        return ResponseEntity.ok(pages);

    }

    @GetMapping("/{hotel_id}/info")
    public final ResponseEntity<HotelDto> searchHotelWithId(@PathVariable Long hotel_id){

        HotelDto hotel= hotelService.getHotelById(hotel_id);

        return ResponseEntity.ok(hotel);
    }

}
