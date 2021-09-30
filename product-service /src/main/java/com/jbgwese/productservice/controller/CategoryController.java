package com.jbgwese.productservice.controller;

import com.jbgwese.productservice.model.Category;
import com.jbgwese.productservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NegativeOrZero;


@RequestMapping("/categories")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //adding product category
    @PostMapping("")
    public Category saveCategory(@PathVariable Category category) {
        return categoryService.saveCategory(category);
    }
}
