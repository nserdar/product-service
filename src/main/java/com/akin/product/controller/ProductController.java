package com.akin.product.controller;

import com.akin.product.payload.request.ProductRequest;
import com.akin.product.payload.response.ProductResponse;
import com.akin.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequest productRequest, BindingResult result) {
        if (result.hasErrors()) {
            // Validasyon hatalarını işlemek için
            StringBuilder errorMessage = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n")
            );
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        // Ürünü kaydetme işlemleri
        productService.createProduct(productRequest);

        return ResponseEntity.ok("Product created successfully");
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @Valid @RequestBody ProductRequest productRequest, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n")
            );
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        productService.updateProduct(id, productRequest);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable int categoryId) {
        List<ProductResponse> products = productService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductResponse>> filterProductsByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<ProductResponse> products = productService.filterProductsByPrice(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }


}
