package com.graduate.mobilekiosk.web.item.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class MenuSaveDto {

    @NotBlank(message = "메뉴 이름은 반드시 존재해야합니다.")
    private String menuName;

    private String description;

    private int menuPrice;

    @NotBlank
    private String categoryName;


}
