package com.maz.store.inventory.service.listeners;

import com.maz.store.inventory.service.allocation.AllocationService;
import com.maz.store.model.inventory.AllocationRequest;
import com.maz.store.model.inventory.AllocationResponse;
import com.maz.store.model.inventory.DeAllocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static com.maz.store.inventory.config.JmsConfig.*;

@Service
@RequiredArgsConstructor
public class AllocationListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = ALLOCATE_ORDER_QUEUE)
    public void listenAllocation(AllocationRequest allocationRequest) {

        AllocationResponse allocationResponse = allocationService.allocate(allocationRequest.getOrderDto());

        jmsTemplate.convertAndSend(ALLOCATION_RESPONSE_QUEUE, allocationResponse);

    }

    @JmsListener(destination = DE_ALLOCATION_QUEUE)
    public void listenDeAllocation(DeAllocationRequest deAllocationRequest) {
        allocationService.deAllocate(deAllocationRequest.getOrderDto());
    }

}
