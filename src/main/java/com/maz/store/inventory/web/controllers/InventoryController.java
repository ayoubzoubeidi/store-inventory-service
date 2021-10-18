package com.maz.store.inventory.web.controllers;

import com.maz.store.inventory.service.InventoryService;
import com.maz.store.model.inventory.ProductInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productId}/id")
    public ResponseEntity getQOH(@PathVariable("productId") UUID productId) {
        return new ResponseEntity(inventoryService.getQuantityOnHandByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("/{upc}/upc")
    public ResponseEntity getQOH(@PathVariable("upc") String upc) {
        return new ResponseEntity(inventoryService.getQuantityOnHandByUpc(upc), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addInventory(@RequestBody ProductInventoryDto inventoryDto) {
        return new ResponseEntity(inventoryService.addInventory(inventoryDto), HttpStatus.CREATED);
    }

}
