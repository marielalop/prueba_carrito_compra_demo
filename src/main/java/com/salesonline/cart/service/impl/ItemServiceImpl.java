package com.salesonline.cart.service.impl;

import com.salesonline.cart.domain.Item;
import com.salesonline.cart.dto.ItemDto;
import com.salesonline.cart.dto.mapper.ItemMapper;
import com.salesonline.cart.service.ItemService;
import com.salesonline.cart.exceptions.ItemException;
import com.salesonline.cart.repository.ItemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for the Item entity.
 */
@Service
@Transactional
@Log4j2
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemMapper itemMapper;

    /**
     * Get one item by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ItemDto findOne(Long id) throws ItemException {
        log.debug("[ItemService] - findOne {} " + "itemId: " + id);

        try{
            return itemMapper.toDto(this.findById(id));
        } catch (ItemException e) {
            throw e;
        } catch (Exception ex) {
            throw new ItemException(String.format("An error occurred while getting a item by id: %s", ex.getMessage()));
        }
    }

    /**
     * Get one item by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Item findById(Long id) throws ItemException {
        log.debug("[ItemService] - findById {} " + "itemId: " + id);

        try{
            return itemRepository.findById(id)
                    .orElseThrow(() -> new ItemException(String.format("There is no item with id %s", id)));
        } catch (Exception ex) {
            throw ex;
        }
    }


    /**
     * Get all Items.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ItemDto> findAll() throws ItemException {
        log.debug("[ItemService] - getAll {}");

        try{
            return itemMapper.toDto(itemRepository.findAll());
        } catch (Exception ex) {
            throw new ItemException(String.format("An error occurred getting the item list: %s", ex.getMessage()));
        }
    }

    /**
     * Save a item.
     *
     * @param itemDto the entity to save
     * @return the persisted entity
     */
    public ItemDto save(ItemDto itemDto) throws ItemException {
        log.debug("Request to save Item : {}", itemDto);

        try{
            Item item = itemMapper.toEntity(itemDto);
            item = itemRepository.save(item);
            return itemMapper.toDto(item);
        } catch (Exception ex) {
            throw new ItemException(String.format("An error occurred while persisting the item: %s", ex.getMessage()));
        }
    }

    /**
     * Delete the item by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) throws ItemException {
        log.info("[ItemService] - deletePeripheralDeviceFromGateway {} " + "itemId: " + id);

        try{
            itemRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ItemException(String.format("An error occurred getting the item list: %s", ex.getMessage()));
        }
    }
}
