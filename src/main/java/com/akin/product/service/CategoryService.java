package com.akin.product.service;

import com.akin.product.model.Category;
import com.akin.product.payload.request.CategoryRequest;
import com.akin.product.payload.response.CategoryResponse;
import com.akin.product.payload.mappers.CategoryMapper; // Mapper'ı ekleyin
import com.akin.product.payload.response.ProductResponse;
import com.akin.product.repository.CategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper; // Mapper'ı ekleyin

    public void createCategory(@Valid CategoryRequest categoryRequest) {
        Category category = categoryMapper.mapCategoryRequestToCategory(categoryRequest);
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::mapCategoryToCategoryResponse) // Mapper kullanarak dönüştürme
                .toList();
    }

    public CategoryResponse getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return categoryMapper.mapCategoryToCategoryResponse(category); // Mapper kullanarak dönüştürme
    }

    public void updateCategory(int id, @Valid CategoryRequest categoryRequest) {
        Category category = categoryMapper.mapCategoryRequestToUpdatedCategory(id, categoryRequest);
        categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    public List<ProductResponse> getProductsByCategory(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        return category.getProducts().stream()
                .map(product -> new ProductResponse(
                        product.getName(),
                        product.getDescription(),
                        product.getQuantity(),
                        product.getPrice().doubleValue(),
                        category.getId(),
                        category.getName()
                ))
                .toList();
    }
}