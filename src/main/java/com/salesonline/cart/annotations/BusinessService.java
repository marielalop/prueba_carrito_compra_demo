package com.salesonline.cart.annotations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.SystemException;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW,
        rollbackFor = {SystemException.class})
public @interface BusinessService {
}
