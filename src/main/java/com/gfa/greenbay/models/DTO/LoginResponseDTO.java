package com.gfa.greenbay.models.DTO;

public class LoginResponseDTO {
  private String status;
  private String token;
  private long balance;

  public LoginResponseDTO() {
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }
}
