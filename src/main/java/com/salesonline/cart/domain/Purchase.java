package com.salesonline.cart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder(builderMethodName = "newInstance")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "purchase"
)
public class Purchase {

    @Id
    @Column(name = "idPurchase")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("purchases")
    private Customer customer;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PurchaseDetail> purchaseDetails = new HashSet<>();




}
