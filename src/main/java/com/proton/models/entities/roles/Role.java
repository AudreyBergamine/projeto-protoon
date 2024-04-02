package com.proton.models.entities.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.proton.models.entities.roles.Permission.ADMIN_CREATE;
import static com.proton.models.entities.roles.Permission.ADMIN_DELETE;
import static com.proton.models.entities.roles.Permission.ADMIN_READ;
import static com.proton.models.entities.roles.Permission.ADMIN_UPDATE;
import static com.proton.models.entities.roles.Permission.MUNICIPE_CREATE;
import static com.proton.models.entities.roles.Permission.MUNICIPE_DELETE;
import static com.proton.models.entities.roles.Permission.MUNICIPE_READ;
import static com.proton.models.entities.roles.Permission.MUNICIPE_UPDATE;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  MUNICIPE_READ,
                  MUNICIPE_UPDATE,
                  MUNICIPE_DELETE,
                  MUNICIPE_CREATE
          )
  ),
  MUNICIPE(
          Set.of(
                  MUNICIPE_READ,
                  MUNICIPE_UPDATE,
                  MUNICIPE_DELETE,
                  MUNICIPE_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
