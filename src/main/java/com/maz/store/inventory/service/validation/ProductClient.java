package com.maz.store.inventory.service.validation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(name = "${product.service.name}")
public interface ProductClient {

    public static String VALIDATION_URL = "/api/v1/products/{upc}/validate";

    @RequestMapping(method = RequestMethod.GET, value = VALIDATION_URL)
    UUID validateOrder(@PathVariable String upc);

}
