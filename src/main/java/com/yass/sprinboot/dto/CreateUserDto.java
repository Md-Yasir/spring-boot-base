package com.yass.sprinboot.dto;

import com.yass.sprinboot.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private List<Role> roles;
}
