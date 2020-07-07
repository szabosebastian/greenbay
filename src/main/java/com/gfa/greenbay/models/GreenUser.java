package com.gfa.greenbay.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GreenUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private long balance;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<Items> ownedItems;

  @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
  private List<Items> boughtItems;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Bid> bids;

  public GreenUser() {
  }

  public GreenUser(String username) {
    this.username = username;
  }

  public GreenUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }

  public List<Items> getOwnedItems() {
    return ownedItems;
  }

  public void setOwnedItems(List<Items> ownedItems) {
    this.ownedItems = ownedItems;
  }

  public List<Items> getBoughtItems() {
    return boughtItems;
  }

  public void setBoughtItems(List<Items> boughtItems) {
    this.boughtItems = boughtItems;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
