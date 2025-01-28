package com.airBnb_application.firstproject.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = @UniqueConstraint(
                name= "Unique_constraints",
                columnNames = {"hotel_id", "room_id","date"}
        ))
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column( nullable = false)
    private LocalDate date;

    @Column( nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer bookedCount;

    @Column( nullable = false)
    private Integer totalCount;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column( nullable = false, precision = 4, scale = 2)
    private BigDecimal surgeFactor;

    @Column( nullable = false)
    private boolean closed;

    @Column( nullable = false, precision = 10, scale = 2)
    private BigDecimal price; // basePrice*surgeFactor

    @Column( nullable = false)
    private String city;



}
