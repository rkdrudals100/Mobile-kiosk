package com.graduate.mobilekiosk.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {

    @NotBlank
    private String categoryName;
}
