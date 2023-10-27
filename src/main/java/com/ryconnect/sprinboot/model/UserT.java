package com.ryconnect.sprinboot.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_t")
public class UserT implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
  @GeneratedValue
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  
  @Column(nullable = false, columnDefinition = "TINYINT(1)")
  private boolean active = true;

  @Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login")
	private Date lastLogin;

  @OneToMany(fetch = FetchType.EAGER, mappedBy="userT")
 private List<UserRoleLinkT> userRoleLinkTs;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
	  List<GrantedAuthority> authorities = new ArrayList<>();
		this.getUserRoleLinkTs().forEach(roleLink -> {
			authorities.add(new SimpleGrantedAuthority(roleLink.getUserRoleT().getRoleName()));
		});
		
      return authorities;
  }

  
  @Override
  public String getPassword() {
    return password;
  }

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
