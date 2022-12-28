package com.sashkou.storage;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class User {

    private final String email;
    private final String password;
    private final Role role;

    public enum Role {
        USER(Set.of(Permission.READ)),
        ADMIN(Set.of(Permission.READ, Permission.WRITER));

        private final Set<Permission> permissions;

        Role(Set<Permission> permissions) {
            this.permissions = permissions;
        }

        public Set<Permission> getPermissions() {
            return permissions;
        }

        public Set<SimpleGrantedAuthority> getAuthorities() {
            return getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                    .collect(Collectors.toSet());
        }

        public enum Permission {
            READ("read"),
            WRITER("write");

            private final String permission;

            Permission(String permission) {
                this.permission = permission;
            }

            public String getPermission() {
                return permission;
            }
        }
    }
}
