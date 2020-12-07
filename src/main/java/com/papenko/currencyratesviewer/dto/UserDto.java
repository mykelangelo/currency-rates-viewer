package com.papenko.currencyratesviewer.dto;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
public class UserDto {
    @Email
    @NotBlank
    String email;
    @NotBlank
    String hash;
}
