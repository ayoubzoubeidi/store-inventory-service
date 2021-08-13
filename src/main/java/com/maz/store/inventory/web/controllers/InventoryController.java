package com.maz.store.inventory.web.controllers;

import com.maz.store.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{upc}")
    public ResponseEntity getQOH(String upc) {
        return new ResponseEntity(inventoryService.getQuantityOnHand(upc), HttpStatus.OK);
    }

}
