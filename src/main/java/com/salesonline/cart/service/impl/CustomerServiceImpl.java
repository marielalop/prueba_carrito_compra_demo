package com.salesonline.cart.service.impl;


import com.salesonline.cart.domain.Customer;
import com.salesonline.cart.dto.CustomerDto;
import com.salesonline.cart.dto.mapper.CustomerMapper;
import com.salesonline.cart.repository.CustomerRepository;
import com.salesonline.cart.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rx.Single;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for the Customer entity.
 */
@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    /**
     * Get all customers.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CustomerDto> findAll() {
        log.debug("Request to get all customers");
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all customers by id.
     * @param id
     * @return Optional of Customer
     */
    @Transactional(readOnly = true)
    public Single<Object> findAllById(Long id) {
        log.debug("Request to get all customers by Id");
        return Single.create(singleSubscriber ->{
            final Optional<CustomerDto> optionalClient = customerRepository.findById(id).map(customerMapper::toDto);
            if (!optionalClient.isPresent()){
                throw new EntityNotFoundException("There's not customer with this id");

            }else {
                singleSubscriber.onSuccess(optionalClient);
            }
        });
    }

    /**
     * Save a customer.
     *
     * @param customerDto the entity to save
     * @return the persisted entity
     */
    public CustomerDto save(CustomerDto customerDto) {
        log.debug("Request to save Customer : {}", customerDto);
        Customer customer = customerMapper.toEntity(customerDto);
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }




}
