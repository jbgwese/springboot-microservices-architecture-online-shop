package com.jbgwese.productservice.service;

import com.jbgwese.productservice.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    List<Product> products = new ArrayList<>();

    public String addProduct(Product product) {
        products.add(product);
        return "successfull";
    }

    public List<Product> productList() {
        return products;
    }

    public List<Product> productListByCategory(String category) {
        return products.stream().filter(product -> product.getCategory().getName().equalsIgnoreCase(category))
                .collect(Collectors.toList());

    }

    public Product productListById(long id) {
        return products.stream().filter(product -> product.getId() == id)
                .findAny()
                .get();
    }

    public String updateProduct(Product product) {
        for (Product prod : products) {
            if (prod.getId() == product.getId()) {

                prod.setName(product.getName());
                prod.setCategory(product.getCategory());
                prod.setPrice(product.getPrice());
                prod.setPrice(product.getPrice());
                prod.setDiscount(product.getPrice());

                return "product updated";
            }
        }
        return "product update failed";
    }

    public String deleteProductById(long id) {
        for (Product prod:products) {

            if (prod.getId()==id){
                products.remove(prod);
              return "product deleted";
            }

        }

        return "product with id "+id+" is not found";
    }
}
