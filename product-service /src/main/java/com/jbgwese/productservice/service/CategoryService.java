package com.jbgwese.productservice.service;

import com.jbgwese.productservice.controller.CategoryController;
import com.jbgwese.productservice.model.Category;
import com.jbgwese.productservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category saveCategory(Category category) {
        return  categoryRepository.save(category);
    }
}
