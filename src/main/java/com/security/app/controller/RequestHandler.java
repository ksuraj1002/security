package com.security.app.controller;

import com.security.app.entities.Credentials;
import com.security.app.entities.Person;
import com.security.app.entities.UsersPrincipal;
import com.security.app.services.CommonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RequestHandler {
    @Autowired
    CommonServices commonServices;

    @GetMapping(value={"/","/home","/index"})
    public String getHomePage(){
        return "index";
    }

    @PostMapping(value={"/register"})
    public String doRegister(Person person, Credentials credentials){
             String password=credentials.getPassword();
             commonServices.saveUser(person,credentials);
             commonServices.autoLogin(credentials.getUserName(),password);
             System.out.println("wait here");
        return "redirect:/dashboard";
    }


    @GetMapping(value={"/dashboard"})
    public String getDashboard(Principal principal){
        String defaultKey = null;
        String[] defaultUrls=principal.toString().split(",");
        
        for(String str:defaultUrls){
            if(str.contains("defaultUrl")){
                defaultKey=str.toString().substring(str.toString().lastIndexOf("=")+1);
                break;
            }
        }
       // defaultUrl.stream().map(x->x.equalsIgnoreCase("defaultUrl")).collect(Collectors.toList());
        System.out.println(defaultKey);
        //String roles=principal.toString().substring(principal.toString().lastIndexOf("[")+1,principal.toString().lastIndexOf("]]"));
        return "redirect:"+defaultKey.trim();
    }

    @GetMapping(value={"/access_denied"})
    public String getAccessDenied(Principal principal){
        //String roles=principal.toString().substring(principal.toString().lastIndexOf("[")+1,principal.toString().lastIndexOf("]]"));
        return "access_denied";
    }

}
