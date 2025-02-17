package com.oficinadobrito.api.configs.exeptions;

import com.oficinadobrito.api.configs.exeptions.base.CustomExceptions;

public class BadRequestException extends CustomExceptions {
    public BadRequestException(String message) {
        super(message);
    }
}
