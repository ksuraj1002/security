package com.security.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(value="/users",method = RequestMethod.GET)
public class UsersController {

    @GetMapping(value={"/dashboard"})
    public ModelAndView getDashboard(Principal principal, ModelAndView mv){
        mv.setViewName("dashboard");
        return mv;
    }
}
