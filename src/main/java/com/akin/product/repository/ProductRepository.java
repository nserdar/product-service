package com.akin.product.repository;

import com.akin.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategoryId(int categoryId);

    List<Product> findByPriceBetween(BigDecimal bigDecimal, BigDecimal bigDecimal1);
}
