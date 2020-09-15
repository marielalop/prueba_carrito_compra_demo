package com.salesonline.cart.dto;

import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PurchaseDto {
    private Long id;
    private ZonedDateTime date;
    private Long customerId;
    private String customerName;

}
