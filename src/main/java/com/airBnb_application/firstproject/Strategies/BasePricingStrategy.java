package com.airBnb_application.firstproject.Strategies;

import com.airBnb_application.firstproject.Entities.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//@Service
@RequiredArgsConstructor

public class BasePricingStrategy implements PricingStrategy{
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getPrice();
    }
}
