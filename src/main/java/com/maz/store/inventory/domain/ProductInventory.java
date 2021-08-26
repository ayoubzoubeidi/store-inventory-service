package com.maz.store.inventory.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductInventory extends BaseEntity {

    @Builder
    public ProductInventory(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, UUID productId,
                         String upc, Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.productId = productId;
        this.upc = upc;
        this.quantityOnHand = quantityOnHand;
    }

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID productId;
    private String upc;
    private Integer quantityOnHand = 0;

}
