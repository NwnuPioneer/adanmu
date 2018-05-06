package org.nwnu.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/Login")
public class LoginController { 

    @RequestMapping(value = "SysLogin")
    public String SysLogin() {
        return "Login/SysLogin";
    }  

}
