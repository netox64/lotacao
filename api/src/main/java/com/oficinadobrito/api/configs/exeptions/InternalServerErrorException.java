package com.oficinadobrito.api.configs.exeptions;

import com.oficinadobrito.api.configs.exeptions.base.CustomExceptions;

import java.time.LocalDate;

public class InternalServerErrorException extends CustomExceptions {

    private static final String NOME_API = "Divulga Potiguar";
    private static final String SPACE = " ";

    public InternalServerErrorException(String message) {
        super(NOME_API  + " \n date:"+ SPACE + LocalDate.now() + " \n Error:" + SPACE + message);
    }
}
