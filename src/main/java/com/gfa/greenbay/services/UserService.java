package com.gfa.greenbay.services;

import com.gfa.greenbay.exceptions.NotPositiveNumberException;
import com.gfa.greenbay.models.GreenUser;
import com.gfa.greenbay.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public GreenUser findUserByToken() {
    GreenUser user = this.userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    return user;
  }

  public void saveGreenUser(GreenUser user) {
    this.userRepository.save(user);
  }

  public GreenUser addAmountToBalance(long amount) throws Exception {
    if (amount < 0) {
      throw new NotPositiveNumberException("The value need to be higher then 0.");
    }
    GreenUser user = findUserByToken();
    user.setBalance(user.getBalance() + amount);
    this.userRepository.save(user);
    return user;
  }
}
