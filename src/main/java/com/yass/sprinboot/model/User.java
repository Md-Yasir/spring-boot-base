package com.yass.sprinboot.model;

import com.yass.sprinboot.enums.Role;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstname;
  @Nullable
  private String middleName;
  private String lastname;
  @Nullable
  private String nickName;
  private String email;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime lastLoginAt;
  private boolean isActive;

  @Enumerated(EnumType.STRING)
  private List<Role> roles;

//  @OneToMany(mappedBy = "user")
//  private List<Token> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
            .flatMap(role -> role.getAuthorities().stream())
            .collect(Collectors.toList());
  }

//  @Override
//  public String getPassword() {
//    return password;
//  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
