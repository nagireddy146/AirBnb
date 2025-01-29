package com.airBnb_application.firstproject.Controllers;

import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.DTO.HotelSearchDto;
import com.airBnb_application.firstproject.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class SearchController {

    private final InventoryService inventoryService;
    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchForHotel(@RequestBody HotelSearchDto hotelSearchDto){

        Page<HotelDto> pages=inventoryService.searchHotels(hotelSearchDto);

        return ResponseEntity.ok(pages);

    }
}
