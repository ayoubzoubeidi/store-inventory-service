package com.maz.store.inventory.service;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.inventory.repositories.ProductInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final ProductInventoryRepository productInventoryRepository;

    @Override
    public Integer getQuantityOnHand(String upc) {
        List<ProductInventory> products = productInventoryRepository.findAllByUpc(upc);
        return products.stream().mapToInt(ProductInventory::getQuantityOnHand).sum();
    }

}
