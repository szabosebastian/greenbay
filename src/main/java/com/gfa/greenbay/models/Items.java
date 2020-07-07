package com.gfa.greenbay.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Items {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private long id;
  private String name;
  private String description;
  private String photoUrl;
  private long startingPrice;
  private long purchasePrice;

  @JsonIgnore
  private boolean sellable;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private GreenUser owner;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private GreenUser buyer;

  @JsonIgnore
  @OneToMany(mappedBy = "items", cascade = CascadeType.ALL)
  private List<Bid> bids;

  public Items() {
    this.sellable = true;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public boolean isSellable() {
    return sellable;
  }

  public void setSellable(boolean sellable) {
    this.sellable = sellable;
  }

  public GreenUser getOwner() {
    return owner;
  }

  public void setOwner(GreenUser owner) {
    this.owner = owner;
  }

  public GreenUser getBuyer() {
    return buyer;
  }

  public void setBuyer(GreenUser buyer) {
    this.buyer = buyer;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
