package com.oficinadobrito.api.configs.exeptions.base;

import java.io.Serial;

public class CustomExceptions extends RuntimeException{
    @Serial
    private static  final long serialVersionUID = 1L;

    public CustomExceptions(String message) {
        super(message);

    }

}