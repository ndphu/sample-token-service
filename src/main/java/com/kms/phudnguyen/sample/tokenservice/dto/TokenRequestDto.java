package com.kms.phudnguyen.sample.tokenservice.dto;

import lombok.Data;

@Data
public class TokenRequestDto {
  Long userId;
  Long accountId;
  String[] roles;
}
