package com.kms.phudnguyen.sample.tokenservice.service;

import com.kms.phudnguyen.sample.tokenservice.dto.TokenRequestDto;
import com.kms.phudnguyen.sample.tokenservice.dto.TokenResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

  @Value("${token-service.time-to-live:3600}")
  Long timeToLive;

  @Value("${token-service.secret-key:}")
  String secret;


  @Override
  public TokenResponseDto generate(TokenRequestDto requestDto) throws UnsupportedEncodingException {

    String jwt = Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, secret)
        .setSubject(requestDto.getUserId().toString())
        .claim("userId", requestDto.getUserId())
        .claim("accountId", requestDto.getAccountId())
        .claim("roles", requestDto.getRoles())
        .setExpiration(DateUtils.addSeconds(new Date(), timeToLive.intValue()))
        .compact();

    return TokenResponseDto.builder()
        .token(jwt)
        .build();
  }

  @Override
  public Jws<Claims> validate(String jwt) throws UnsupportedEncodingException {
    return Jwts.parser().setSigningKey(secret)
        .parseClaimsJws(jwt);
  }
}
