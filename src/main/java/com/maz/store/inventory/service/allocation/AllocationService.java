package com.maz.store.inventory.service.allocation;

import com.maz.store.model.order.OrderDto;

public interface AllocationService {

    void allocate(OrderDto order);

    void deAllocate(OrderDto order);

}
