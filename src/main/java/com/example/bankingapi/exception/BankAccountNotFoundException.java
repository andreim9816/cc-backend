package com.example.bankingapi.exception;

public class BankAccountNotFoundException extends RuntimeException {
  public BankAccountNotFoundException(String message) {
    super(message);
  }
}
