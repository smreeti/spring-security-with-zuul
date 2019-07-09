package com.f1soft.authservice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smriti on 7/3/19
 */

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "csdghs";
    }
}
