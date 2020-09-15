package com.salesonline.cart.controller;

import com.salesonline.cart.dto.CustomerDto;
import com.salesonline.cart.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {

	private final CustomerService clientService;

	public CustomerController(CustomerService clientService) {
		this.clientService = clientService;
	}

    public static void writeLog(String text) {

        log.error(text);

    }

    /**
     * GET  /customers : get all the customers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     */
    @GetMapping("")
    public List<CustomerDto> getAllClients() {
        log.debug("REST request to get all customers");
        return clientService.findAll();
    }


    @GetMapping(
            value = "{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<Object> findAllById(@PathVariable("customerId") String id) {
        log.debug("REST request to get all customers");
        return clientService.findAllById(Long.parseLong(id));
    }

    /**
     * POST  /customers : Create a new customer.
     *
     * @param customerDTO the customerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("")
    public ResponseEntity<CustomerDto> createClient(@RequestBody CustomerDto customerDTO) throws URISyntaxException {
        log.debug("REST request to save a Customer : {}", customerDTO);

        CustomerDto result = clientService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
                .body(result);
    }




}
