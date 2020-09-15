package com.salesonline.cart.controller;

import com.salesonline.cart.dto.ItemDto;
import com.salesonline.cart.dto.PurchaseDto;
import com.salesonline.cart.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PurchaseController {

	private final PurchaseService purchaseService;

	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

    public static void writeLog(String text) {
        log.error(text);
    }

    /**
     * GET  /purchases : get all the purchases.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of purchases in body
     */
    @GetMapping("/purchases")
    public List<PurchaseDto> getAllSales() {
        log.debug("REST request to get all purchases");
        return purchaseService.findAll();
    }

    @GetMapping("/purchases/{id}")
    public Single<List<PurchaseDto>> getAllSalesByUserId(@PathVariable Long id) {
        log.debug("Request to get all purchases from userId : {}", id);
        return purchaseService.findAllPurchasesById(id);
    }

    /**
     * POST  /purchases : Create a new sale.
     *
     * @param purchaseDTO the purchaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new purchaseDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/purchases")
    public ResponseEntity<PurchaseDto> createSale(@RequestBody PurchaseDto purchaseDTO) throws URISyntaxException {
        log.debug("REST request to save a Purchase : {}", purchaseDTO);

        PurchaseDto result = purchaseService.save(purchaseDTO);
        return ResponseEntity.created(new URI("/api/purchases/" + result.getId()))
                .body(result);
    }

    /**
     * POST  /purchases : Create a new purchaseDetail.
     *
     * @param products the item list of sale to create
     * @param clientId the id of customer
     * @return the ResponseEntity with status 201 (Created) and with body the new saleDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @PostMapping("/sales/detail/{customerId}")
    public ResponseEntity<PurchaseDto> createSaleDetail(@RequestBody List<ItemDto> items, @PathVariable Long customerId) throws URISyntaxException {
        log.debug("REST request to save a PurchaseDetail : {}", customerId);
        PurchaseDto result = purchaseService.createPurchaseDetail(customerId, items);
        return ResponseEntity.created(new URI("/api/purchases/detail/" + result.getId()))
                .body(result);
    }
}
