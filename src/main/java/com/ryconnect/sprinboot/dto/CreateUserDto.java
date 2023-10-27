package com.ryconnect.sprinboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.ryconnect.sprinboot.enums.Role;

@Data
@Builder
public class CreateUserDto {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
}
