package com.proton.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proton.models.repositories.MunicipeRepository;

@Service
public class MunicipeDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private final MunicipeRepository municipeRepository;

  public MunicipeDetailsServiceImpl(MunicipeRepository municipeRepository) {
    this.municipeRepository = municipeRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return municipeRepository.findByEmail(username)
        .map(municipe -> new MunicipeAuthenticated(municipe))
        .orElseThrow(
            () -> new UsernameNotFoundException("\n\nUsuário não encontrado com nome de " + username + "\n\n"));
  }

}
