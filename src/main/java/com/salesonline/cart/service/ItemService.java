package com.salesonline.cart.service;

import com.salesonline.cart.dto.ItemDto;
import com.salesonline.cart.exceptions.ItemException;

import java.util.List;

/**
 * Service interface for the Customer entity.
 */
public interface ItemService {

    ItemDto save(ItemDto itemDto) throws ItemException;

    List<ItemDto> findAll() throws ItemException;

    ItemDto findOne(Long id) throws ItemException;

    void delete(Long id) throws ItemException;
}
