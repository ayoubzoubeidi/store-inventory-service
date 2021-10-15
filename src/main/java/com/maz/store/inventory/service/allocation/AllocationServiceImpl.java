package com.maz.store.inventory.service.allocation;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.inventory.repositories.ProductInventoryRepository;
import com.maz.store.model.inventory.AllocationResponse;
import com.maz.store.model.order.OrderDto;
import com.maz.store.model.order.OrderLineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocationServiceImpl implements AllocationService {

    private final ProductInventoryRepository inventoryRepository;


    @Override
    public AllocationResponse allocate(OrderDto order) {

        try {
            var partialAllocation = false;

            for (OrderLineDto orderLine : order.getOrderLines()) {
                List<ProductInventory> inventory = inventoryRepository.findAllByUpc(orderLine.getUpc());
                var quantityToAllocate = orderLine.getOrderQuantity();
                for (ProductInventory inventoryLine : inventory) {

                    if (quantityToAllocate > 0) {

                        if (inventoryLine.getQuantityOnHand() <= quantityToAllocate) {
                            quantityToAllocate -= inventoryLine.getQuantityOnHand();
                            inventoryRepository.delete(inventoryLine);
                        } else {
                            var restInventory = inventoryLine.getQuantityOnHand() - quantityToAllocate;
                            quantityToAllocate = 0;
                            inventoryLine.setQuantityOnHand(restInventory);
                            inventoryRepository.saveAndFlush(inventoryLine);
                            break;
                        }

                    }
                }
                orderLine.setQuantityAllocated(orderLine.getOrderQuantity() - quantityToAllocate);
                partialAllocation = quantityToAllocate != 0;
            }

            return AllocationResponse.builder()
                    .order(order).partialAllocation(partialAllocation).allocationError(false).build();
        } catch (Exception ex) {
            return AllocationResponse.builder()
                    .order(order).partialAllocation(false).allocationError(false).build();
        }
    }

    @Override
    public void deAllocate(OrderDto order) {
        for (OrderLineDto orderLine : order.getOrderLines()) {
            if (orderLine.getQuantityAllocated() > 0) {
                inventoryRepository.saveAndFlush(
                        ProductInventory.builder().productId(orderLine.getProductId()).quantityOnHand(orderLine.getQuantityAllocated()).build()
                );
            }
        }
    }
}
