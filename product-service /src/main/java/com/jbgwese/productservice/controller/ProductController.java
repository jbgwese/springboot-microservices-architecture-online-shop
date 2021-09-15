package com.jbgwese.productservice.controller;

import com.jbgwese.productservice.model.Product;
import com.jbgwese.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/v1/")
@Data
@NoArgsConstructor
public class ProductController {
    private ProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    //endpoint to add a product

    @PostMapping("/addProduct")
    ResponseEntity<Product> addProduct(@RequestBody Product product) {
        String status = productService.addProduct(product);
        logger.info("Product added status  -{}" + status);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    //endpoint to list all products
    @GetMapping("/productList")
    List<Product> productList() {
        return productService.productList();
    }


    //endpoint to list all products by

    @GetMapping("/productList/{category}")
    List<Product> productListByCategory(@PathVariable String category) {
        return productService.productListByCategory(category);
    }

    //endpoint to list product by id
    @GetMapping("/productList/{id}")
    public Product productListById(@PathVariable long id) {
        return productService.productListById(id);
    }

    //endpoint to update products
    @PutMapping("/updateProduct")

    public String updateProduct(@RequestBody Product product){

        return productService.updateProduct(product);
    }

    //endpoint to delete product

    @DeleteMapping("/deleteProductById/{id}")
    public String deleteProductById(@PathVariable long id){

        return productService.deleteProductById(id);
    }


}
