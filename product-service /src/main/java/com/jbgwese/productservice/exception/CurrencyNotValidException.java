package com.jbgwese.productservice.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class CurrencyNotValidException extends RuntimeException {
    public CurrencyNotValidException(String s) {
        super(s);
    }
}
