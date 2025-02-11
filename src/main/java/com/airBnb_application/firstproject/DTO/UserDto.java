package com.airBnb_application.firstproject.DTO;

import com.airBnb_application.firstproject.Entities.Enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Roles> roles;
}
