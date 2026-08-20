package com.nilsonSantiago.api_transacao.services.exceptions;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException() {
    }

    public UnprocessableEntityException(String msg) {
        super(msg);
    }

}