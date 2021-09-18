package com.security.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(value="/admin",method = RequestMethod.GET)
public class AdminController {
    @GetMapping(value={"/dashboard"})
    public ModelAndView getDashboard(Principal principal, ModelAndView mv){
        mv.setViewName("/admin/dashboard");
        return mv;
    }

    @GetMapping(value={"/addedvalue"})
    public ModelAndView getAddedValue(Principal principal, ModelAndView mv){
        int x =9/2;
        mv.setViewName("/admin/addedvalue");
        mv.addObject("res",x);
        return mv;
    }

}
