package com.salesonline.cart.service.impl;

import com.salesonline.cart.domain.Customer;
import com.salesonline.cart.repository.CustomerRepository;
import com.salesonline.cart.apierror.MyError;
import com.salesonline.cart.dto.CustomerDto;
import com.salesonline.cart.dto.mapper.CustomerMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Rule
    public ExpectedException fails = ExpectedException.none();

    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl service;

    Customer customer = Customer.newInstance()
            .id(TestConstants.CUSTOMER_ID)
            .email("test@gmail.com")
            .name("Juan")
            .lastName("Perez")
            .dni("79081234567")
            .phone("78923456")
            .build();

    CustomerDto customerDto = CustomerDto.newInstance()
            .id(TestConstants.CUSTOMER_ID)
            .email("test@gmail.com")
            .name("Juan")
            .lastName("Perez")
            .dni("79081234567")
            .phone("78923456")
            .build();

    @Before
    public void setUp() throws Exception {

        when(customerMapper.toEntity(customerDto)).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDto);

        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
    }

    @Test
    public void whenSaveItemAllParamsSet() {

        CustomerDto clientdto = service.save(customerDto);

        verify(customerRepository, times(1))
                .save(customer);
        verify(customerMapper, times(1))
                .toEntity(customerDto);
        verify(customerMapper, times(1))
                .toDto(customer);

        assertEquals(clientdto.getId(), TestConstants.CUSTOMER_ID);
    }

    @Test
    public void findAll() {

        List<CustomerDto> customerDtoList = service.findAll();

        verify(customerRepository, times(1))
                .findAll();

        verify(customerMapper, times(1))
                .toDto(customer);

        assertTrue(!customerDtoList.isEmpty());
        assertTrue(customerDtoList.size() > 0);
    }

    @Test(expected = AssertionError.class)
    public void findAllById() {

        when(customerRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Customer()));

        service.findAllById(TestConstants.CUSTOMER_ID)
                .test()
                .assertCompleted()
                .assertError(MyError.class)
                .awaitTerminalEvent();

        verify(customerRepository, times(1)).findById(any(Long.class));

    }

}