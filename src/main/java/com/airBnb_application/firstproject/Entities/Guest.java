package com.airBnb_application.firstproject.Entities;

import com.airBnb_application.firstproject.Entities.Enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    private Integer age;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
