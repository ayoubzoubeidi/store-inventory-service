package com.maz.store.inventory.bootstrap;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.inventory.repositories.ProductInventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Bootstrap  {

    private final ProductInventoryRepository inventoryRepository;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            inventoryRepository.saveAndFlush(ProductInventory.builder().productId(UUID.randomUUID()).upc("1234").quantityOnHand(50).build());
        };
    }
}
