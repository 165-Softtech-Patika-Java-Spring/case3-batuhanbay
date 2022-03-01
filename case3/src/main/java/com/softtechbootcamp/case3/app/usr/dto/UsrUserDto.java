package com.softtechbootcamp.case3.app.usr.dto;

import com.softtechbootcamp.case3.app.usr.enums.UsrUserType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UsrUserDto {
    @NotNull(message = "Username parameter can not be null")
    @NotBlank(message = "Username parameter can not be blank")
    private String username;
    @NotNull(message = "Mail parameter can not be null")
    @NotBlank(message = "Mail parameter can not be blank")
    @Email(message = "Email should be valid")
    private String mail;
    @NotNull(message = "Phone number parameter can not be null")
    @NotBlank(message = "Phone number parameter can not be blank")
    private String phoneNumber;
    private UsrUserType usrUserType;
}
