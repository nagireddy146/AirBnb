package com.airBnb_application.firstproject.Controllers;


import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.Services.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@Slf4j
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto){
        log.info("Creating a hotel with name {}", hotelDto.getName());
        HotelDto NewhotelDto= hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(NewhotelDto, HttpStatus.CREATED);
    }

    @GetMapping("/{hotel_id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotel_id){
        HotelDto hotel= hotelService.getHotelById(hotel_id);

        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{hotel_id}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable Long hotel_id,@RequestBody HotelDto hotelDto){
        HotelDto hotel = hotelService.updateHotelById(hotel_id,hotelDto);

        return ResponseEntity.ok(hotel);

    }

    @DeleteMapping("/{hotel_id}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotel_id){
       hotelService.deleteHotelById(hotel_id);

        return  ResponseEntity.noContent().build();

    }

    @PatchMapping("{hotel_id}")
    public ResponseEntity<Void> activateHotel(@PathVariable Long hotel_id){
        hotelService.activateHotel(hotel_id);
        return  ResponseEntity.noContent().build();
    }

}
