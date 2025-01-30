package com.airBnb_application.firstproject.Entities;

import com.airBnb_application.firstproject.Entities.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name ="hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn( name= "room_id", nullable = false)
    private Room room;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column( nullable = false)
    private Integer rooms_count;

    @Column( nullable = false)
    private LocalDate checkInDate;

    @Column( nullable = false)
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private BigDecimal amount;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus booking_status;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn( name="payment_id")
//    private Payment payment;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booking_guest",
            joinColumns = @JoinColumn(name="booking_id"),
            inverseJoinColumns = @JoinColumn(name="Guest_id")
    )
    private Set<Guest> guest;


}

