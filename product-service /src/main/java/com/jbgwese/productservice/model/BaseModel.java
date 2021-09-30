package com.jbgwese.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseModel implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
}
