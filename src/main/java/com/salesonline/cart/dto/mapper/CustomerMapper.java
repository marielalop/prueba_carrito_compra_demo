package com.salesonline.cart.dto.mapper;

import com.salesonline.cart.domain.Customer;
import com.salesonline.cart.dto.CustomerDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Customer and its DTO ClientDTO.
 */
@Mapper( componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer> {

}
