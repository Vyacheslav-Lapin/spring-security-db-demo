package ru.vlapin.demo.springsecuritydbdemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class MyController {

//  AccountRepository accountRepository;

  @Secured("ROLE_ADMIN")
  @GetMapping("hello")
  public String hello() {
    return "Hello from Controller";
  }
}
