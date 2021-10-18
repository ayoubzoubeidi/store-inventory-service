package com.maz.store.inventory.web.mappers;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.model.inventory.ProductInventoryDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(InventoryMapperDecorator.class)
public interface InventoryMapper {

    ProductInventory productDtoToProductInventory(ProductInventoryDto dto);

    ProductInventoryDto productInventoryToProductInventoryDto(ProductInventory productInventory);

}
