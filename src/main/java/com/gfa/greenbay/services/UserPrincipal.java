package com.gfa.greenbay.services;

import com.gfa.greenbay.models.GreenUserDetails;
import com.gfa.greenbay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserPrincipal implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserPrincipal(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    return new GreenUserDetails(userRepository.findByUsername(username));
  }
}