package com.akin.product.payload.mappers;

import com.akin.product.model.Category;
import com.akin.product.model.Product;
import com.akin.product.payload.request.ProductRequest;
import com.akin.product.payload.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    public Product mapProductRequestToProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .price(BigDecimal.valueOf(productRequest.getPrice()))
                .category(new Category(productRequest.getCategoryId(), productRequest.getCategoryName())) // Kategori nesnesi oluşturma
                .build();
    }

    public ProductResponse mapProductToProductResponse(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice().doubleValue(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }

    public Product mapProductRequestToUpdatedProduct(int id, ProductRequest productRequest) {
        return Product.builder()
                .id(id) // ID'yi güncelleme işlemi için kullanıyoruz
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .price(BigDecimal.valueOf(productRequest.getPrice()))
                .category(new Category(productRequest.getCategoryId(), productRequest.getCategoryName())) // Kategori nesnesi oluşturma
                .build();
    }
}