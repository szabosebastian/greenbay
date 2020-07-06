package com.gfa.greenbay.exceptions;

public class MissingParamsException extends Exception{
  public MissingParamsException() {
  }

  public MissingParamsException(String message) {
    super(message);
  }
}
