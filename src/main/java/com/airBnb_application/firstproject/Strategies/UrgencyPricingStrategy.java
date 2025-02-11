package com.airBnb_application.firstproject.Strategies;

import com.airBnb_application.firstproject.Entities.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
//@Service
@RequiredArgsConstructor
public class UrgencyPricingStrategy implements PricingStrategy {

    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price= wrapped.calculatePrice(inventory);
        LocalDate date=inventory.getDate();
        if(date.isBefore(LocalDate.now().plusDays(7))){
            return price.multiply(BigDecimal.valueOf(1.25));
        } return price;
    }
}
