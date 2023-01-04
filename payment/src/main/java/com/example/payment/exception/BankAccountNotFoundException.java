package com.example.payment.exception;

public class BankAccountNotFoundException extends RuntimeException {
  public BankAccountNotFoundException(String message) {
    super(message);
  }
}
