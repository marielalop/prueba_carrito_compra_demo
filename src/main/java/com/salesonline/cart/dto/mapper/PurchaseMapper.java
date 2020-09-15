package com.salesonline.cart.dto.mapper;

import com.salesonline.cart.domain.Purchase;
import com.salesonline.cart.dto.PurchaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Purchase and its DTO PurchaseDto.
 */
@Mapper( componentModel = "spring")
public interface PurchaseMapper extends EntityMapper<PurchaseDto, Purchase> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    PurchaseDto toDto(Purchase purchase);

    @Mapping(source = "customerId", target = "customer.id")
    Purchase toEntity(PurchaseDto purchaseDTO);

    default Purchase fromId(Long id) {
        if (id == null) {
            return null;
        }
        Purchase purchase = new Purchase();
        purchase.setId(id);
        return purchase;
    }

}
