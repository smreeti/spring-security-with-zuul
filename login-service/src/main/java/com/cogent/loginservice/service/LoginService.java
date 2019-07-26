package com.cogent.loginservice.service;

import com.cogent.loginservice.requestDTO.LoginRequestDTO;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

     String login(LoginRequestDTO requestDTO, HttpServletRequest request);

}
