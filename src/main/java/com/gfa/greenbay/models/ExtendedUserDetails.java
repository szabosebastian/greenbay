package com.gfa.greenbay.models;

import org.springframework.security.core.userdetails.UserDetails;

public interface ExtendedUserDetails extends UserDetails {

  long getId();
}
