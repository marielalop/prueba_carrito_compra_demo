package com.salesonline.cart.controller.utils;

import com.salesonline.cart.controller.*;

public class ControllerMapper {

    public static void writeLog(String name, String text) {
        if (name.contains(CustomerController.class.getCanonicalName())) {
            CustomerController.writeLog(text);

        } else {
            if (name.contains(PurchaseController.class.getCanonicalName())) {
                PurchaseController.writeLog(text);
            } else {
                if (name.contains(ItemsController.class.getCanonicalName())) {
                    ItemsController.writeLog(text);

                } else {
                    if (name.contains(UserController.class.getCanonicalName())) {
                        UserController.writeLog(text);

                    }
                }
            }
        }
    }
}