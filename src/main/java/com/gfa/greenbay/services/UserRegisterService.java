package com.gfa.greenbay.services;

import com.gfa.greenbay.exceptions.MissingParamsException;
import com.gfa.greenbay.exceptions.PasswordTooShortException;
import com.gfa.greenbay.exceptions.UserNameAlreadyExistException;
import com.gfa.greenbay.models.DTO.RegisterDTO;
import com.gfa.greenbay.models.GreenUser;
import com.gfa.greenbay.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

  private final UserRepository userRepository;
  private final ErrorService errorService;

  public UserRegisterService(UserRepository userRepository,
                             ErrorService errorService) {
    this.userRepository = userRepository;
    this.errorService = errorService;
  }

  public GreenUser registerNewUser(RegisterDTO registerDTO) throws Exception {
    if (errorService.checkClassNullFields(registerDTO).size() > 0) {
      throw new MissingParamsException(errorService.buildMissingFieldErrorMessage(errorService.checkClassNullFields(registerDTO)));
    } else if (this.userRepository.existsGreenUserByUsername(registerDTO.getUsername())) {
      throw new UserNameAlreadyExistException("Username is already taken.");
    } else if (registerDTO.getPassword().length() < 8) {
      throw new PasswordTooShortException("Password must be at least 8 characters.");
    } else {
      return createNewUser(registerDTO);
    }
  }

  private GreenUser createNewUser(RegisterDTO registerDTO) {
    GreenUser user = new GreenUser(registerDTO.getUsername(), new BCryptPasswordEncoder().encode(registerDTO.getPassword()));
    this.userRepository.save(user);
    return user;
  }

}
