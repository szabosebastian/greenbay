package com.gfa.greenbay.models.DTO;


public class BidResponseDTO {
  private String name;
  private long bid;

  public BidResponseDTO() {
  }

  public BidResponseDTO(String name, long bid) {
    this.name = name;
    this.bid = bid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getBid() {
    return bid;
  }

  public void setBid(long bid) {
    this.bid = bid;
  }
}
