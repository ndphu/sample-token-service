package com.kms.phudnguyen.sample.tokenservice.service;

import com.kms.phudnguyen.sample.tokenservice.dto.TokenRequestDto;
import com.kms.phudnguyen.sample.tokenservice.dto.TokenResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.io.UnsupportedEncodingException;

public interface TokenService {
  TokenResponseDto generate(TokenRequestDto requestDto) throws UnsupportedEncodingException;

  Jws<Claims> validate(String jwt) throws UnsupportedEncodingException;
}
