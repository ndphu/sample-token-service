package com.kms.phudnguyen.sample.tokenservice.controller;

import com.kms.phudnguyen.sample.tokenservice.dto.TokenRequestDto;
import com.kms.phudnguyen.sample.tokenservice.dto.TokenResponseDto;
import com.kms.phudnguyen.sample.tokenservice.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/token-service")
public class TokenController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

  private final TokenService tokenService;

  @Autowired
  public TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @PostMapping(value = "/generate", consumes = "application/json")
  public TokenResponseDto generate(@RequestBody TokenRequestDto tokenRequest) throws UnsupportedEncodingException {
    LOGGER.info("Generating token for user {} and account {}",
        tokenRequest.getUserId(),
        tokenRequest.getAccountId());
    return tokenService.generate(tokenRequest);
  }

  @PostMapping(value = "/validate", consumes = "text/plain")
  public Jws<Claims> validate(@RequestBody String jwt) throws UnsupportedEncodingException {
    LOGGER.info("Validating token {}", jwt);
    return tokenService.validate(jwt);
  }
}
