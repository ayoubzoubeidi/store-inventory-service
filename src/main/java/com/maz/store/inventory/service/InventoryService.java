package com.maz.store.inventory.service;

import java.util.UUID;

public interface InventoryService {

    Integer getQuantityOnHandByProductId(UUID productId);
    Integer getQuantityOnHandByUpc(String upc);

}
