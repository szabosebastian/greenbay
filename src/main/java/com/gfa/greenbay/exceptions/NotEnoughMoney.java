package com.gfa.greenbay.exceptions;

public class NotEnoughMoney extends Exception{
  public NotEnoughMoney() {
  }

  public NotEnoughMoney(String message) {
    super(message);
  }
}
