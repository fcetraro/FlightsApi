package com.ml.FlightsApi.exception.implementation;

public class NotValidPaymentMethodException extends RuntimeException {
    public NotValidPaymentMethodException(Throwable err) {
        super("Medio de pago no valido.", err);
    }
}
