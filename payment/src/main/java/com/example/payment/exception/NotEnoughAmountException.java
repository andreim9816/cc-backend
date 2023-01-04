package com.example.payment.exception;

public class NotEnoughAmountException extends RuntimeException {
  public NotEnoughAmountException(String message) {
    super(message);
  }
}
