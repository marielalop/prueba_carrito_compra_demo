package com.salesonline.cart.service.impl;

import com.salesonline.cart.SalesOnlineApplication;
import com.salesonline.cart.dto.PurchaseDto;
import com.salesonline.cart.exceptions.ItemException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalesOnlineApplication.class)
public class PurchaseServiceImplTest implements TestConstants {

    @Autowired
    private PurchaseServiceImpl service;

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findOneById() throws ItemException {
        Optional<PurchaseDto> item =  service.findOne(null);

    }
}