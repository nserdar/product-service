package com.akin.product.payload.mappers;

import com.akin.product.model.Category;
import com.akin.product.payload.request.CategoryRequest;
import com.akin.product.payload.response.CategoryResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CategoryMapper {

    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .build();
    }

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .build();
    }

    public Category mapCategoryRequestToUpdatedCategory(int id, CategoryRequest categoryRequest) {
        return Category.builder()
                .id(id) // ID'yi güncelleyerek kullanma
                .name(categoryRequest.getName())
                .build();
    }
}