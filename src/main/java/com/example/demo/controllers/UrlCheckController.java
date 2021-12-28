package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
@RestController
public class UrlCheckController {
    private final String SITE_IS_UP = "site is up!";
    private final String SITE_IS_DOWN = "site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage= "";
        try {
            URL urlObj= new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecodeCategory = conn.getResponseCode()/100;
            if (responsecodeCategory !=2 || responsecodeCategory!=3) {
                returnMessage = SITE_IS_DOWN;
            } else{
                return SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }
}
