package com.backend.app.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
    private boolean enabled = true;
    private CredentialsDto credentials;
    private AttributesDto attributes;
}
