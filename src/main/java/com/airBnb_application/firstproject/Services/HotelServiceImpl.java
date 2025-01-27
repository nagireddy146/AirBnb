package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.DTO.HotelDto;
import com.airBnb_application.firstproject.Entities.Hotel;
import com.airBnb_application.firstproject.Exception.ResourceNotFoundException;
import com.airBnb_application.firstproject.Repositories.HotelRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.BooleanNode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Getter
@Setter
@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info(" Creating a hotel with name {}",hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);

        hotel = hotelRepository.save(hotel);
        log.info("created a user with the details{}",hotel.getId());

        return modelMapper.map(hotel, HotelDto.class);

        }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info(" Getting the hotel with Id {} ",id);
        Hotel hotel = hotelRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id "   +id));

        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info(" Updating the hotel with Id {} ",id);
        Hotel hotel = hotelRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id "   +id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        boolean isExists = hotelRepository.existsById(id);
        if(!isExists) throw new ResourceNotFoundException("Hotel not found with Id "   +id);

        hotelRepository.deleteById(id);
        //TODO : Delete the future inventories for this ID

    }
}
