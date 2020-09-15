package com.salesonline.cart.repository;

import com.salesonline.cart.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Purchase entity.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByCustomer_Id(Long id);
}
