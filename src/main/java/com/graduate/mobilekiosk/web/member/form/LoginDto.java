package com.graduate.mobilekiosk.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
