package com.kms.phudnguyen.sample.tokenservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDto {
  String token;
}
