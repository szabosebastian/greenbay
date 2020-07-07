package com.gfa.greenbay.models.DTO;

public class ItemListDTO {
  private String name;
  private String photoUrl;
  private long lastBid;

  public ItemListDTO() {
  }

  public ItemListDTO(String name, String photoUrl, long lastBid) {
    this.name = name;
    this.photoUrl = photoUrl;
    this.lastBid = lastBid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public long getLastBid() {
    return lastBid;
  }

  public void setLastBid(long lastBid) {
    this.lastBid = lastBid;
  }
}
