package com.backend.app.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDto {
    private boolean temporary;
    private String type = "password";
    private String value;
}
