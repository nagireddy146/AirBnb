package com.airBnb_application.firstproject.Strategies;

import com.airBnb_application.firstproject.Entities.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    public BigDecimal calculatePrice(Inventory inventory);
}
