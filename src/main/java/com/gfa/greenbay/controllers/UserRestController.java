package com.gfa.greenbay.controllers;

import com.gfa.greenbay.models.DTO.LoginRequestDTO;
import com.gfa.greenbay.models.DTO.RegisterDTO;
import com.gfa.greenbay.services.UserLoginService;
import com.gfa.greenbay.services.UserRegisterService;
import com.gfa.greenbay.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  private final UserRegisterService userRegisterService;
  private final UserLoginService userLoginService;
  private final UserService userService;

  public UserRestController(UserRegisterService userRegisterService,
                            UserLoginService userLoginService,
                            UserService userService) {
    this.userRegisterService = userRegisterService;
    this.userLoginService = userLoginService;
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity registerUser(@RequestBody RegisterDTO registerDTO) throws Exception {
    return ResponseEntity.ok().body(userRegisterService.registerNewUser(registerDTO));
  }

  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestBody LoginRequestDTO loginDTO) throws Exception {
    return ResponseEntity.ok().body(userLoginService.loginUser(loginDTO));
  }

  @PostMapping("/add")
  public ResponseEntity addAmountToBalance(@RequestParam(name = "balance", required = false) long amount) throws Exception {
    System.out.println(amount);
    return ResponseEntity.ok().body(userService.addAmountToBalance(amount));
  }
}
