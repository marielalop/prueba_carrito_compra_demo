package com.salesonline.cart.repository;

import com.salesonline.cart.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Item entity.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
