package com.gfa.greenbay.exceptions;

public class UsernameNotFoundOrInvalidPasswordException extends Exception{
  public UsernameNotFoundOrInvalidPasswordException() {
  }

  public UsernameNotFoundOrInvalidPasswordException(String message) {
    super(message);
  }
}
