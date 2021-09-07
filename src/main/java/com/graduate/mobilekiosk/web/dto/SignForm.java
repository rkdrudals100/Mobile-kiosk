package com.graduate.mobilekiosk.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignForm {

    private String userId;
    private String password;
    private String passwordCheck;
}
