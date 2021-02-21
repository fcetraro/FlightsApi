package com.ml.FlightsApi.exception.implementation;

public class NotValidEmailException extends RuntimeException {
    public NotValidEmailException(Throwable err) {
        super("Email no valido.", err);
    }
}
