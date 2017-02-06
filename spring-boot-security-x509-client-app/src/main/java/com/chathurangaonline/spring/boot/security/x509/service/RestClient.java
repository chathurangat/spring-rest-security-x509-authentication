package com.chathurangaonline.spring.boot.security.x509.service;

import org.springframework.web.client.RestTemplate;

public class RestClient
{
    //REST api endpoint
    private static final String REST_API_ENDPOINT = "https://localhost:8443/logged_info";


    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

                    public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                        return hostname.equals("localhost");
                    }
                });
    }


    public static String getRemoteMessage()
    {
        //configuring the keyStore and trustStore for the client application
        System.setProperty("javax.net.ssl.keyStore", "/Users/chathuranga/Projects/spring-boot-ssl-x509/keystores/clientkeystore.p12");
        System.setProperty("javax.net.ssl.keyStorePassword", "3edc4rfv");
        System.setProperty("javax.net.ssl.trustStore", "/Users/chathuranga/Projects/spring-boot-ssl-x509/keystores/clienttruststore.p12");
        System.setProperty("javax.net.ssl.trustStorePassword", "3edc4rfv");

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(REST_API_ENDPOINT, String.class);
    }
}
