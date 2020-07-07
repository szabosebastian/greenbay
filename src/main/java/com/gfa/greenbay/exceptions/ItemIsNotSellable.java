package com.gfa.greenbay.exceptions;

public class ItemIsNotSellable extends Exception{
  public ItemIsNotSellable() {
  }

  public ItemIsNotSellable(String message) {
    super(message);
  }
}
