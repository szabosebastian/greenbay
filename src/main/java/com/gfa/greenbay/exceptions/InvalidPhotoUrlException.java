package com.gfa.greenbay.exceptions;

public class InvalidPhotoUrlException extends Exception{
  public InvalidPhotoUrlException() {
  }

  public InvalidPhotoUrlException(String message) {
    super(message);
  }
}
