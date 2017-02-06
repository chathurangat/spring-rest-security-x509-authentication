package com.chathurangaonline.spring.boot.security.x509.contoller;

import com.chathurangaonline.spring.boot.security.x509.service.RestClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String displayMessage()
    {
        String remoteMessage = RestClient.getRemoteMessage();
        return "Message from REST Api [" + remoteMessage;
    }
}
