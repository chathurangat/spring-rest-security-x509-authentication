package com.chathurangaonline.spring.boot.security.x509.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController
{
    @RequestMapping(value = "/logged_info", method = RequestMethod.GET)
    public String helloController()
    {
        String loggedUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            loggedUser = ((UserDetails) principal).getUsername();
        } else {
            loggedUser = principal.toString();
        }
        return "X.509 authentication done! REST client has been identified as ["+loggedUser+"] ";
    }
}
