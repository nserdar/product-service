package com.akin.product.service;

import com.akin.product.exception.ResourceNotFoundException;
import com.akin.product.model.Product;
import com.akin.product.payload.request.ProductRequest;
import com.akin.product.payload.response.ProductResponse;
import com.akin.product.payload.mappers.ProductMapper; // Import ProductMapper
import com.akin.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper; // ProductMapper nesnesi

    public void createProduct(@Valid ProductRequest productRequest) {
        Product product = productMapper.mapProductRequestToProduct(productRequest);
        productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            // Eğer ürün bulunamadıysa özel bir işlem yap, hata fırlat veya boş liste döndür
            throw new ResourceNotFoundException("No products found."); // Özel bir hata fırlat
            // veya loglama :
            // log.warn("No products found in the database.");
            // return Collections.emptyList();
        }

        return products.stream()
                .map(productMapper::mapProductToProductResponse) // Mapper kullanarak dönüştürme
                .toList();
    }

    public ProductResponse getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.mapProductToProductResponse(product); // Mapper kullanarak dönüştürme
    }

    public void updateProduct(int id, @Valid ProductRequest productRequest) {
        Product updatedProduct = productMapper.mapProductRequestToUpdatedProduct(id, productRequest);
        productRepository.save(updatedProduct);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<ProductResponse> getProductsByCategory(int categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream()
                .map(productMapper::mapProductToProductResponse) // Mapper kullanarak dönüştürme
                .toList();
    }

    public List<ProductResponse> filterProductsByPrice(double minPrice, double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(BigDecimal.valueOf(minPrice), BigDecimal.valueOf(maxPrice));
        return products.stream()
                .map(productMapper::mapProductToProductResponse) // Mapper kullanarak dönüştürme
                .toList();
    }
}