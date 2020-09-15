package com.salesonline.cart.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private Float price;
}
