package de.frudisch.domain;

/**
 * Created by Frudisch on 09.04.2017.
 */
public enum Role {
    ADMIN("ADMIN_PER"), USER("USER_PER");

    private String permission;

    Role(String permission) {
        this.permission = permission;
    }
}
