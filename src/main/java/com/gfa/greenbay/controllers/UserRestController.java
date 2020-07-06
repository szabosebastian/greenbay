package com.gfa.greenbay.controllers;

import com.gfa.greenbay.models.DTO.LoginRequestDTO;
import com.gfa.greenbay.models.DTO.RegisterDTO;
import com.gfa.greenbay.services.UserLoginService;
import com.gfa.greenbay.services.UserRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  private final UserRegisterService userRegisterService;
  private final UserLoginService userLoginService;

  public UserRestController(UserRegisterService userRegisterService,
                            UserLoginService userLoginService) {
    this.userRegisterService = userRegisterService;
    this.userLoginService = userLoginService;
  }

  @PostMapping("/register")
  public ResponseEntity registerUser(@RequestBody RegisterDTO registerDTO) throws Exception {
    return ResponseEntity.ok().body(userRegisterService.registerNewUser(registerDTO));
  }

  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestBody LoginRequestDTO loginDTO) throws Exception {
    return ResponseEntity.ok().body(userLoginService.loginUser(loginDTO));
  }
}
