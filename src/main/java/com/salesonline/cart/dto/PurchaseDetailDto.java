package com.salesonline.cart.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PurchaseDetailDto {
    private Long id;
    private Long saletId;
    private Long productId;
}
