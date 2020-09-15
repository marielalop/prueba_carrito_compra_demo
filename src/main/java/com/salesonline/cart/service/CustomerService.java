package com.salesonline.cart.service;

import com.salesonline.cart.dto.CustomerDto;
import rx.Single;

import java.util.List;

/**
 * Service interface for the Customer entity.
 */
public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);

    List<CustomerDto> findAll();

    Single<Object> findAllById(Long id);
}
