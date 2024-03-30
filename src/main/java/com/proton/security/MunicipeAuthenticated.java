package com.proton.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.proton.models.entities.Municipe;

public class MunicipeAuthenticated implements UserDetails {
  private final Municipe municipe;

  public MunicipeAuthenticated(Municipe municipe) {
    this.municipe = municipe;
  }

  @Override
  public String getUsername() {
    return municipe.getEmail();
  }

  @Override
  public String getPassword() {
    System.out.println(municipe.getSenha());
    return municipe.getSenha();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.municipe.getRole()));
  }

  //Os quatro métodos abaixo são obrigatórios pela Interface, por enquanto não estamos usando
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
