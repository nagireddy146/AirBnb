package com.airBnb_application.firstproject.DTO;

import com.airBnb_application.firstproject.Entities.Enums.Gender;
import com.airBnb_application.firstproject.Entities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
    private LocalDateTime createdAt;
}
