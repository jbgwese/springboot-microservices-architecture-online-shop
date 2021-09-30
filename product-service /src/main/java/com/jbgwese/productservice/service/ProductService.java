package com.jbgwese.productservice.service;

import com.jbgwese.productservice.config.ProductConfiguration;
import com.jbgwese.productservice.exception.CurrencyNotValidException;
import com.jbgwese.productservice.exception.OfferNotValidException;
import com.jbgwese.productservice.model.Product;
import com.jbgwese.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    private ProductConfiguration productConfiguration;

    public ResponseEntity<Product> saveProduct(Product product) {
        log.info("-------------------adding product-----------");
        if(product.getPrice()==0 && product.getDiscount()>0) {
            throw new OfferNotValidException("no discount allowed for Product with price 0");
        }
        List<String> validCurrencies = new ArrayList<>();

        if(!productConfiguration.getCurrencies().contains(product.getCurrency().toUpperCase())){
            throw new CurrencyNotValidException("Invalid currency. Valid currencies are "+ productConfiguration.getCurrencies());
        }

      return  ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    public List<Product> listProducts() {
        log.info("-------------listing products------------");
        return productRepository.findAll();
    }

   /* public Optional<Product> updateProduct(String id) {
     Optional<Product> product=productRepository.findById(id);
         productRepository.save(product);
    }*/
}
