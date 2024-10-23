package com.akin.product.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {

    private String name;
    private String description;
    private int quantity;
    private double price;
    private int categoryId;
    private String categoryName;

}
