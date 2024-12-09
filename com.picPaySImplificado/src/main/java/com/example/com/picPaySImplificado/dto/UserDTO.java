package com.example.com.picPaySImplificado.dto;

import com.example.com.picPaySImplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO<userType>(String firsName, String lastName, String document, BigDecimal balance, String email, String password,  UserType userType) {


}
