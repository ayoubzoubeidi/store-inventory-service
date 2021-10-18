package com.maz.store.inventory.web.mappers;

import com.maz.store.inventory.domain.ProductInventory;
import com.maz.store.inventory.service.validation.ProductClient;
import com.maz.store.model.inventory.ProductInventoryDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class InventoryMapperDecorator implements InventoryMapper {

    //TODO create mapper decorator and use openFeign to make http request to populate productId.
    private InventoryMapper inventoryMapper;
    private ProductClient productClient;

    @Autowired
    public void setInventoryMapper(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    @Autowired
    public void setProductClient(ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public ProductInventory productDtoToProductInventory(ProductInventoryDto dto) {
        ProductInventory productInventory = inventoryMapper.productDtoToProductInventory(dto);
        productInventory.setProductId(productClient.validateOrder(dto.getUpc()));
        System.err.println("HELLO  ");
        System.err.println("HELLO  " + productClient.validateOrder(dto.getUpc()));
        return productInventory;
    }

}
