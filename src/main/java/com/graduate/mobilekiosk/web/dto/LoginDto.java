package com.graduate.mobilekiosk.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
