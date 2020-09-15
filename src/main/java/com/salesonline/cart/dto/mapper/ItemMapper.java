package com.salesonline.cart.dto.mapper;

import com.salesonline.cart.domain.Item;
import com.salesonline.cart.dto.ItemDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Item and its DTO ItemDto.
 */
@Mapper( componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDto, Item> {

}
