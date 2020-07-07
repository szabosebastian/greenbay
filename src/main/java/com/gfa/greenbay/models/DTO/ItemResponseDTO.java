package com.gfa.greenbay.models.DTO;

import com.gfa.greenbay.models.Bid;
import java.util.List;

public class ItemResponseDTO {
  private String name;
  private String description;
  private String photoUrl;
  private long startingPrice;
  private long purchasePrice;
  private List<BidResponseDTO> bids;
  private String seller;
  private String buyer;

  public ItemResponseDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public long getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(long startingPrice) {
    this.startingPrice = startingPrice;
  }

  public long getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(long purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public List<BidResponseDTO> getBids() {
    return bids;
  }

  public void setBids(List<BidResponseDTO> bids) {
    this.bids = bids;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }
}
