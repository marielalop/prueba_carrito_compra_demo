package com.salesonline.cart.repository;

import com.salesonline.cart.domain.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Purchase entity.
 */
@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

}
