package com.example.bankingapi.exception;

public class DifferentCurrencyException extends RuntimeException {
  public DifferentCurrencyException(String message) {
    super(message);
  }
}
