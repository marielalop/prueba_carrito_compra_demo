package com.salesonline.cart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "purchaseDetail"
)
public class PurchaseDetail {

    @Id
    @Column(name = "idPurchaseDetail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("purchase")
    private Purchase purchase;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("purchaseDetails")
    private Item item;
}
