package com.graduate.mobilekiosk.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
public class MemberSaveDto {
    @NotNull
    @Pattern(regexp="[a-zA-Z1-9]{4,20}", message = "영어나 숫자로 4~20자리 이내로 입력해주세요.")
    private String username;

    @NotNull
    @Size(min = 4, max = 8, message = "비밀번호는 4~8자리 사이로 입력해주세요.")
    private String password;

    @NotNull
    private String checkPassword;

    @NotNull
    @Pattern(regexp="[a-z|A-Z|1-9]{1,20}", message = "영어나 숫자로 포함해서 20자리 이내로 입력해주세요.")
    private String url;
}
