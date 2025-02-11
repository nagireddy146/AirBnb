package com.airBnb_application.firstproject.Strategies;

import com.airBnb_application.firstproject.Entities.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//@Service
@RequiredArgsConstructor
public class SurgeFactorStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price= wrapped.calculatePrice(inventory);

        return price.multiply(BigDecimal.valueOf(1.2));

    }
}
