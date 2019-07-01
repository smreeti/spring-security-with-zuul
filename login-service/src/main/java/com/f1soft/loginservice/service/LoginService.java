package com.f1soft.loginservice.service;

import com.f1soft.loginservice.requestDTO.LoginRequestDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

     String login(LoginRequestDTO requestDTO, HttpServletRequest request);

}
