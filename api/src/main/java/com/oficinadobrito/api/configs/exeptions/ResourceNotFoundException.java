package com.oficinadobrito.api.configs.exeptions;

import com.oficinadobrito.api.configs.exeptions.base.CustomExceptions;

public class ResourceNotFoundException extends CustomExceptions {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
