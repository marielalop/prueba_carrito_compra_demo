package com.salesonline.cart.service.impl;

import com.salesonline.cart.SalesOnlineApplication;
import com.salesonline.cart.domain.Item;
import com.salesonline.cart.exceptions.ItemException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalesOnlineApplication.class)
public class ItemServiceImplTest implements TestConstants {

    @Autowired
    private ItemServiceImpl service;

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findOneById() throws ItemException {
      Item item =  service.findById(null);
      }
}