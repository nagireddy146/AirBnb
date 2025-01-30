package com.airBnb_application.firstproject.Services;

import com.airBnb_application.firstproject.DTO.BookingDto;
import com.airBnb_application.firstproject.DTO.BookingObject;
import com.airBnb_application.firstproject.DTO.GuestDto;
import com.airBnb_application.firstproject.Entities.*;
import com.airBnb_application.firstproject.Entities.Enums.BookingStatus;
import com.airBnb_application.firstproject.Exception.ResourceNotFoundException;
import com.airBnb_application.firstproject.Repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{
    private final ModelMapper modelMapper;

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final BookingRespository bookingRepository;
    private final GuestRepository guestRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public BookingDto initializeBooking(BookingObject bookingObject) {

        Hotel hotel = hotelRepository.
                findById(bookingObject.getHotel_id())
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found"));

        Room room = roomRepository
                .findByIdAndHotelId( bookingObject.getRoom_id(),bookingObject.getHotel_id())
                .orElseThrow(()-> new ResourceNotFoundException(" Hotel & room not found with the given details"));

        List<Inventory> inventoriesList = inventoryRepository.findAndLockRooms(
                bookingObject.getHotel_id(),
                bookingObject.getRoom_id(),
                bookingObject.getStartDate(),
                bookingObject.getEndDate(),
                bookingObject.getNumberOfRooms()
        );

        long daysCount= ChronoUnit.DAYS.between(bookingObject.getStartDate(),bookingObject.getEndDate())+1;

        if(inventoriesList.size()!=daysCount){
            throw new IllegalStateException("Rooms are not available anywhere");
        }

        //Reserving the rooms

        for(Inventory inventory: inventoriesList){
            inventory.setBookedCount(inventory.getBookedCount()+ bookingObject.getNumberOfRooms());

        }

        inventoryRepository.saveAll(inventoriesList);


        //Create the booking
        User user=new User();
        user.setId(1L);
        //userRepository.save(user);
        Booking booking = Booking.builder()
                .booking_status(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingObject.getStartDate())
                .checkOutDate(bookingObject.getEndDate())
                .rooms_count(bookingObject.getNumberOfRooms())
                .user(user)
                .amount(BigDecimal.valueOf(800L))
                .build();

        bookingRepository.save(booking);

        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    public BookingDto addGuestsToBooking(Long bookingId, List<GuestDto> guests) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new ResourceNotFoundException(" Booking not found with ID +bookingId"));

        if(checkBookingStatus(booking)){
            throw new IllegalStateException("Booking is already Expired");
        }
        if(booking.getBooking_status()!= BookingStatus.RESERVED){
            throw new IllegalStateException(" Booking status is permitted for adding guests");

        }

        for(GuestDto guestDto: guests){
            User user=new User();
            user.setId(1L);
            guestDto.setUser(user);
            Guest guest = modelMapper.map(guestDto, Guest.class);
            Guest saved_guest = guestRepository.save(guest);
            booking.getGuest().add(saved_guest);

        }
        booking.setBooking_status(BookingStatus.GUESTS_ADDED);
        bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);

    }

    private boolean checkBookingStatus(Booking booking) {

        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }
}
