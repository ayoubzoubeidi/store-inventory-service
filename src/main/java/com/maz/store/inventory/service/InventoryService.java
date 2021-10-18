package com.maz.store.inventory.service;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.model.inventory.ProductInventoryDto;

import java.util.UUID;

public interface InventoryService {

    Integer getQuantityOnHandByProductId(UUID productId);
    Integer getQuantityOnHandByUpc(String upc);
    ProductInventoryDto addInventory(ProductInventoryDto inventoryDto);
}
