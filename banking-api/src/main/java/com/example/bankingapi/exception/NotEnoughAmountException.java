package com.example.bankingapi.exception;

public class NotEnoughAmountException extends RuntimeException {
  public NotEnoughAmountException(String message) {
    super(message);
  }
}
