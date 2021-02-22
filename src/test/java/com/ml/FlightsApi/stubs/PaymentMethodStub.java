package com.ml.FlightsApi.stubs;


import com.ml.FlightsApi.model.PaymentMethodDTO;

public class PaymentMethodStub {
    public static PaymentMethodDTO getCreditPaymentMethod(){
        PaymentMethodDTO payment = new PaymentMethodDTO();
        payment.setDues(5);
        payment.setNumber("1234-1234-1234-1234");
        payment.setType("CREDIT");
        return payment;
    }
    public static PaymentMethodDTO getInvalidCreditPaymentMethod(){
        PaymentMethodDTO payment = new PaymentMethodDTO();
        payment.setDues(0);
        payment.setNumber("1234-1234-1234-1234");
        payment.setType("CREDIT");
        return payment;
    }
    public static PaymentMethodDTO getDebitPaymentMethod(){
        PaymentMethodDTO payment = new PaymentMethodDTO();
        payment.setDues(1);
        payment.setNumber("1234-1234-1234-1234");
        payment.setType("DEBIT");
        return payment;
    }
    public static PaymentMethodDTO getInvalidDebitPaymentMethod(){
        PaymentMethodDTO payment = new PaymentMethodDTO();
        payment.setDues(2);
        payment.setNumber("1234-1234-1234-1234");
        payment.setType("DEBIT");
        return payment;
    }
}
