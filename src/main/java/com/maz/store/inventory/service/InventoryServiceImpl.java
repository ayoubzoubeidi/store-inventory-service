package com.maz.store.inventory.service;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.inventory.repositories.ProductInventoryRepository;
import com.maz.store.inventory.web.mappers.InventoryMapper;
import com.maz.store.model.inventory.ProductInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final ProductInventoryRepository productInventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public Integer getQuantityOnHandByProductId(UUID productId) {
        return productInventoryRepository.findAllByProductId(productId)
                .stream()
                .mapToInt(ProductInventory::getQuantityOnHand)
                .sum();
    }

    @Override
    public Integer getQuantityOnHandByUpc(String upc) {
        List<ProductInventory> products = productInventoryRepository.findAllByUpc(upc);
        return products.stream().mapToInt(ProductInventory::getQuantityOnHand).sum();
    }

    @Override
    public ProductInventoryDto addInventory(ProductInventoryDto inventoryDto) {
        var savedInventory =
                productInventoryRepository
                        .saveAndFlush(inventoryMapper.productDtoToProductInventory(inventoryDto));
        return inventoryMapper.productInventoryToProductInventoryDto(savedInventory);
    }

}
