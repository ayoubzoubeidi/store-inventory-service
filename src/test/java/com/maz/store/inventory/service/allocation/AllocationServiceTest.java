package com.maz.store.inventory.service.allocation;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.inventory.repositories.ProductInventoryRepository;
import com.maz.store.model.order.OrderDto;
import com.maz.store.model.order.OrderLineDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AllocationServiceTest {

    public static final String UPC = "1234";

    @Autowired
    ProductInventoryRepository inventoryRepository;

    @Autowired
    AllocationServiceImpl allocationService;

    @BeforeEach
    void setUp() {
        instantiateDB();
    }

    @Test
    void allocate() {

        List<OrderLineDto> orderLines = new ArrayList<>();

        OrderLineDto orderLine = OrderLineDto.builder().upc(UPC).orderQuantity(20).build();

        orderLines.add(orderLine);
        OrderDto orderDto = OrderDto.builder().orderLines(orderLines).build();

        allocationService.allocate(orderDto);

        assertEquals(20, orderDto.getOrderLines().get(0).getQuantityAllocated());
    }


    private void instantiateDB() {
        inventoryRepository.saveAndFlush(ProductInventory.builder().upc(UPC).quantityOnHand(5).productId(UUID.randomUUID()).build());
        inventoryRepository.saveAndFlush(ProductInventory.builder().upc(UPC).quantityOnHand(5).productId(UUID.randomUUID()).build());
        inventoryRepository.saveAndFlush(ProductInventory.builder().upc(UPC).quantityOnHand(5).productId(UUID.randomUUID()).build());
        inventoryRepository.saveAndFlush(ProductInventory.builder().upc(UPC).quantityOnHand(5).productId(UUID.randomUUID()).build());
    }

}