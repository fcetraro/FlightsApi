package com.ml.FlightsApi.exception.implementation;

public class NotValidAmountException extends RuntimeException {
    public NotValidAmountException(Throwable err) {
        super("Cantidad de personas no valida.", err);
    }
}
