package com.salesonline.cart.controller;

import com.salesonline.cart.dto.ItemDto;
import com.salesonline.cart.service.ItemService;
import com.salesonline.cart.exceptions.ItemException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class ItemsController {

    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    public static void writeLog(String text) {
        log.error(text);
    }

    /**
     * GET  /items : get items list.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of items in body
     */
    @GetMapping("/items")
    public List<ItemDto> getAllProducts() throws ItemException {
        log.debug("REST request to get all Items");
        return itemService.findAll();
    }

    /**
     * GET  /items/:id : pass the item "id" .
     *
     * @param id the id of the itemtDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the itemtDTO
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDto> getProduct(@PathVariable Long id) throws ItemException {
        log.debug("REST request to get an Item : {}", id);

        ItemDto itemDTO = itemService.findOne(id);
        return new ResponseEntity<>(itemDTO,HttpStatus.OK);
    }

    /**
     * POST  /items : Create a new item.
     *
     * @param itemDTO the itemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new itemDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/items")
    public ResponseEntity<ItemDto> createProduct(@RequestBody ItemDto itemDTO) throws URISyntaxException, ItemException {
        log.debug("REST request to save an Item : {}", itemDTO);

        ItemDto result = itemService.save(itemDTO);
        return ResponseEntity.created(new URI("/api/items/" + result.getId()))
                .body(result);
    }

    /**
     * PUT  /items : Updates an existing item.
     *
     * @param itemDTO the itemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated itemDTO
     */
    @PutMapping("/items")
    public ResponseEntity<ItemDto> updateProduct(@RequestBody ItemDto itemDTO) throws ItemException {
        log.debug("REST request to update an Item : {}", itemDTO);
        ItemDto result = itemService.save(itemDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * DELETE  /items/:id : delete the items by "id" .
     *
     * @param id the id of the itemtDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws ItemException {
        log.debug("REST request to delete an Item : {}", id);
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }

}
