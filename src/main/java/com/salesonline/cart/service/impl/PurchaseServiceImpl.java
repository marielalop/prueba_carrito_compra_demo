package com.salesonline.cart.service.impl;

import com.salesonline.cart.EntityNotFoundException;
import com.salesonline.cart.domain.Purchase;
import com.salesonline.cart.domain.PurchaseDetail;
import com.salesonline.cart.dto.ItemDto;
import com.salesonline.cart.dto.PurchaseDto;
import com.salesonline.cart.dto.mapper.ItemMapper;
import com.salesonline.cart.dto.mapper.PurchaseMapper;
import com.salesonline.cart.repository.PurchaseDetailRepository;
import com.salesonline.cart.repository.PurchaseRepository;
import com.salesonline.cart.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import rx.Single;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for the Purchase entity.
 */
@Service
@Transactional
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;

    private PurchaseMapper purchaseMapper;
    private ItemMapper itemMapper;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseDetailRepository purchaseDetailRepository, PurchaseMapper purchaseMapper, ItemMapper itemMapper) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.purchaseMapper = purchaseMapper;
        this.itemMapper = itemMapper;
    }


    /**
     * Get all purchases.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PurchaseDto> findAll() {
        log.debug("Request to get all purchases");
        return purchaseRepository.findAll().stream()
                .map(purchaseMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all purchases by id.
     *
     * @param id the id of the entity
     * @return Optional of Purchase
     */
    @Transactional(readOnly = true)
    public Optional<PurchaseDto> findOne(Long id) {
        log.debug("Request to get Purchase : {}", id);
        return purchaseRepository.findById(id)
                .map(purchaseMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Single<List<PurchaseDto>> findAllPurchasesById(Long id) {
        log.debug("Request to get all purchases from userId : {}", id);
        return findAllSalesInRepository(id)
                .map(this::toBookResponseList);
    }

    private Single<List<Purchase>> findAllSalesInRepository(Long id) {
        return Single.create(singleSubscriber -> {
            List<Purchase> purchases = purchaseRepository.findAllByCustomer_Id(id);

            if (!ObjectUtils.isEmpty(purchases)) {
                singleSubscriber.onSuccess(purchases);
            } else {
                singleSubscriber.onError(new EntityNotFoundException(Purchase.class));
            }
        });
    }

    private List<PurchaseDto> toBookResponseList(List<Purchase> bookList) {
        return bookList
                .stream()
                .map(purchaseMapper:: toDto)
                .collect(Collectors.toList());
    }

    /**
     * Create a new PurchaseDetail.
     *
     * @param items the item list of purchase to create
     * @param customerId the id of customer
     * @return the persisted entity
     */
    @Override
    public PurchaseDto createPurchaseDetail(Long customerId, List<ItemDto> items) {
        PurchaseDto purchase = save(PurchaseDto.builder().customerId(customerId).date(ZonedDateTime.now()).build());
        for (ItemDto item : items) {
            PurchaseDetail purchaseDetail =  new PurchaseDetail();
            purchaseDetail.setItem(itemMapper.toEntity(item));
            purchaseDetail.setPurchase(purchaseMapper.toEntity(purchase));
            purchaseDetailRepository.save(purchaseDetail);
        }
        return purchase;
    }

    /**
     * Save a purchase.
     *
     * @param purchaseDto the entity to save
     * @return the persisted entity
     */
    public PurchaseDto save(PurchaseDto purchaseDto) {
        log.debug("Request to save Purchase : {}", purchaseDto);
        Purchase purchase = purchaseMapper.toEntity(purchaseDto);
        purchase = purchaseRepository.save(purchase);
        return purchaseMapper.toDto(purchase);
    }


}
