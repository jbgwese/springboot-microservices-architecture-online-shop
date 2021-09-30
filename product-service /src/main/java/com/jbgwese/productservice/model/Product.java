package com.jbgwese.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends NamedModel {
    @NotNull(message = "category can not be null")
    private Category category;
    @Min(0)
    private double price;
    @Min(0)
    private double discount;
    private String discountDescription;
    private String currency;


}
