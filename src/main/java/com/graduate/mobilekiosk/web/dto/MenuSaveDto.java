package com.graduate.mobilekiosk.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MenuSaveDto {

    private String menuName;

    private String description;

    @NotBlank
    private String categoryName;


}
