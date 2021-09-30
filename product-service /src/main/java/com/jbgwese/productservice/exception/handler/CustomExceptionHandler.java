package com.jbgwese.productservice.exception.handler;

import com.jbgwese.productservice.dto.APIErro;
import com.jbgwese.productservice.exception.CurrencyNotValidException;
import com.jbgwese.productservice.exception.OfferNotValidException;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({OfferNotValidException.class, CurrencyNotValidException.class})
    ResponseEntity<?> offerNotValidHandler(Exception exc, ServletWebRequest request) {
        APIErro apiErro = new APIErro();
        apiErro.setTimestamp(LocalDateTime.now());
        apiErro.setPathUri(request.getDescription(true));
        apiErro.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiErro.setErrors(Arrays.asList(exc.getClass() + ":" + exc.getMessage()));
        return new ResponseEntity(apiErro, new HttpHeaders(), apiErro.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        APIErro apiErro = new APIErro();
        apiErro.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiErro.setTimestamp(LocalDateTime.now());
        apiErro.setPathUri(request.getDescription(false));
        List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
        List<String> errors =  fieldError.stream().map(fieldError1 -> fieldError1.getField() + ":"
                + fieldError1.getDefaultMessage()).collect(Collectors.toList());
        apiErro.setErrors(errors);
        return  new ResponseEntity<>(apiErro,headers,apiErro.getHttpStatus());
    }
}
