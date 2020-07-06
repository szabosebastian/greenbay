package com.gfa.greenbay.exceptions;

public class UserNameAlreadyExistException extends Exception{

  public UserNameAlreadyExistException() {
  }

  public UserNameAlreadyExistException(String message) {
    super(message);
  }
}
