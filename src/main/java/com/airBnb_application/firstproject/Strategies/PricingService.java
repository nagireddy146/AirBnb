package com.airBnb_application.firstproject.Strategies;

import com.airBnb_application.firstproject.Entities.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//@Service
@RequiredArgsConstructor
public class PricingService {

    public BigDecimal calculateDynamicPricing(Inventory inventory){
        PricingStrategy pricingStrategy = new BasePricingStrategy();

        pricingStrategy = new SurgeFactorStrategy(pricingStrategy);

        pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);
    }
}
