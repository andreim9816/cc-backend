package com.example.payment.exception;

public class DifferentCurrencyException extends RuntimeException {
  public DifferentCurrencyException(String message) {
    super(message);
  }
}
