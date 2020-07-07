package com.gfa.greenbay.services;

import com.gfa.greenbay.exceptions.MissingParamsException;
import com.gfa.greenbay.exceptions.UsernameNotFoundOrInvalidPasswordException;
import com.gfa.greenbay.models.DTO.LoginRequestDTO;
import com.gfa.greenbay.models.DTO.LoginResponseDTO;
import com.gfa.greenbay.models.ExtendedUserDetails;
import com.gfa.greenbay.models.GreenUser;
import com.gfa.greenbay.repositories.UserRepository;
import com.gfa.greenbay.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

  private final ErrorService errorService;
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;

  public UserLoginService(ErrorService errorService, UserRepository userRepository, AuthenticationManager authenticationManager) {
    this.errorService = errorService;
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
  }

  public LoginResponseDTO loginUser(LoginRequestDTO loginDTO) throws Exception {
    if (errorService.checkClassNullFields(loginDTO).size() > 0) {
      throw new MissingParamsException(errorService.buildMissingFieldErrorMessage(errorService.checkClassNullFields(loginDTO)));
    } else if (!this.userRepository.existsGreenUserByUsername(loginDTO.getUsername())) {
      throw new UsernameNotFoundOrInvalidPasswordException("Username not found.");
    } else {
      return authentication(loginDTO);
    }
  }

  private LoginResponseDTO authentication(LoginRequestDTO loginDTO) throws Exception {
    try {
      this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
    } catch (BadCredentialsException e) {
      throw new UsernameNotFoundOrInvalidPasswordException("Password is incorrect.");
    }
    final ExtendedUserDetails userDetails = new UserPrincipal(userRepository).loadUserByUsername(loginDTO.getUsername());
    GreenUser user = this.userRepository.findByUsername(loginDTO.getUsername());
    LoginResponseDTO successUser = new LoginResponseDTO();
    successUser.setStatus("ok");
    successUser.setToken(new JwtUtil().generateToken(userDetails));
    successUser.setBalance(user.getBalance());
    return successUser;
  }
}
