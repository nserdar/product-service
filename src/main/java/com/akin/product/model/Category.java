package com.akin.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Category(@NotNull(message = "Category ID is required") int categoryId, @NotBlank(message = "Category name cannot be empty") String categoryName) {
    }
}
/*
cascade = CascadeType.ALL, orphanRemoval = true: Bu ayar ile bir Category silindiğinde ilişkili
Product kayıtları da otomatik olarak silinir. Aynı zamanda, orphanRemoval = true ile, bir kategoriye
 bağlı olan ama artık bir kategoriye ait olmayan ürünler de veritabanından otomatik olarak silinir.
 */