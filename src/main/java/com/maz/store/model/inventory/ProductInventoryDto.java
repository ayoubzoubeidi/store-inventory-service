package com.maz.store.model.inventory;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductInventoryDto {

    private UUID id;
    private String upc;
    private UUID productId;
    private Integer quantityOnHand;

}
