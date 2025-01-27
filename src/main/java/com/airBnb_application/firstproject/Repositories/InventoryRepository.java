package com.airBnb_application.firstproject.Repositories;

import com.airBnb_application.firstproject.Entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
