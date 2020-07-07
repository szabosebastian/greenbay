package com.gfa.greenbay.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long bid;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private Items items;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  private GreenUser user;

  public Bid() {
  }

  public Bid(long bid) {
    this.bid = bid;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getBid() {
    return bid;
  }

  public void setBid(long bid) {
    this.bid = bid;
  }

  public Items getItems() {
    return items;
  }

  public void setItems(Items items) {
    this.items = items;
  }

  public GreenUser getUser() {
    return user;
  }

  public void setUser(GreenUser greenUser) {
    this.user = greenUser;
  }

}
