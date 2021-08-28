package com.maz.store.inventory.service.listeners;

import com.maz.store.inventory.service.allocation.AllocationService;
import com.maz.store.model.inventory.AllocationRequest;
import com.maz.store.model.inventory.DeAllocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import static com.maz.store.inventory.config.JmsConfig.ALLOCATE_ORDER_QUEUE;
import static com.maz.store.inventory.config.JmsConfig.DE_ALLOCATION_QUEUE;

@Service
@RequiredArgsConstructor
public class AllocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = ALLOCATE_ORDER_QUEUE)
    public void listenAllocation(AllocationRequest allocationRequest) {
        allocationService.allocate(allocationRequest.getOrderDto());
    }

    @JmsListener(destination = DE_ALLOCATION_QUEUE)
    public void listenDeAllocation(DeAllocationRequest deAllocationRequest) {
        allocationService.deAllocate(deAllocationRequest.getOrderDto());
    }

}
