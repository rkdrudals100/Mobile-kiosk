package com.graduate.mobilekiosk.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MenuSaveDto {

    @NotBlank(message = "메뉴 이름은 반드시 존재해야합니다.")
    private String menuName;

    private String description;

    @NotBlank
    private String categoryName;


}
