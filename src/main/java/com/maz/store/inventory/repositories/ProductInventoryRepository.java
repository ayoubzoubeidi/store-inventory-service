package com.maz.store.inventory.repositories;

import com.maz.store.inventory.domain.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, UUID> {

    List<ProductInventory> findAllByUpc(String upc);
}
