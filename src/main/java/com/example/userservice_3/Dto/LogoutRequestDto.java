package com.example.userservice_3.Dto;

import com.example.userservice_3.models.Token;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LogoutRequestDto {
    private String token;
}
