package com.gfa.greenbay.exceptions;

public class PasswordTooShortException extends Exception{
  public PasswordTooShortException() {
  }

  public PasswordTooShortException(String message) {
    super(message);
  }
}
