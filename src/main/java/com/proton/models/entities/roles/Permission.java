package com.proton.models.entities.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MUNICIPE_READ("municipe:read"),
    MUNICIPE_UPDATE("municipe:update"),
    MUNICIPE_CREATE("municipe:create"),
    MUNICIPE_DELETE("municipe:delete")

    ;

    @Getter
    private final String permission;
}
