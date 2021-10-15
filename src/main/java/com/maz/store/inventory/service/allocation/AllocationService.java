package com.maz.store.inventory.service.allocation;

import com.maz.store.model.inventory.AllocationResponse;
import com.maz.store.model.order.OrderDto;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface AllocationService {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    AllocationResponse allocate(OrderDto order);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    void deAllocate(OrderDto order);

}
