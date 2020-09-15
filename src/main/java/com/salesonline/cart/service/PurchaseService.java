package com.salesonline.cart.service;

import com.salesonline.cart.dto.ItemDto;
import com.salesonline.cart.dto.PurchaseDto;
import rx.Single;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for the Purchase entity.
 */
public interface PurchaseService {

    PurchaseDto save(PurchaseDto clientDto);

    List<PurchaseDto> findAll();

    Optional<PurchaseDto> findOne(Long id);

    Single<List<PurchaseDto>> findAllPurchasesById(Long id);

    PurchaseDto createPurchaseDetail(Long clientId, List<ItemDto> products);
}
