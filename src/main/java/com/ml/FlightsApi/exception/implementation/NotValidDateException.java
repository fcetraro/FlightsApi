package com.ml.FlightsApi.exception.implementation;

public class NotValidDateException extends RuntimeException {
    public NotValidDateException(Throwable err) {
        super("Fechas no validas.", err);
    }
}
