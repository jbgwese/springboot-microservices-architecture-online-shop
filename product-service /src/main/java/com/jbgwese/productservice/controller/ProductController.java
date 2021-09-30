package com.jbgwese.productservice.controller;

import com.jbgwese.productservice.model.Product;
import com.jbgwese.productservice.service.ProductService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/")
@Data
@NoArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    //saving prodcut --endpoint
    @PostMapping("")
    ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        return productService.saveProduct(product);
    }
// listing products
    @GetMapping("")
    List<Product> listProducts(){
        return  productService.listProducts();
    }

  /*  @PutMapping("/{id}")
    Optional<Product> updateProduct(@PathVariable String id){
        return productService.updateProduct(id);
    }*/
}
