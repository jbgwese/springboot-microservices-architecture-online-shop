package com.jbgwese.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIErro {
    private HttpStatus httpStatus;
    private List<String> errors;
    private LocalDateTime timestamp;
    private String pathUri;
}
