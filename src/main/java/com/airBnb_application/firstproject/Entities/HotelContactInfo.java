package com.airBnb_application.firstproject.Entities;


import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable

public class HotelContactInfo {

   private String address;
   private String phoneNumber;
   private String email;
   private String location;
}
