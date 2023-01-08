package com.sashkou.controller;

import com.sashkou.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class Controller {

    private final EventsService service;

    @GetMapping
    public com.sashkou.service.Events getEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, Principal principal) throws Exception {
        String email = ((OAuth2AuthenticationToken) principal).getPrincipal().getAttribute("email");
        String accessToken = oAuth2Client.getAccessToken().getTokenValue();

        return service.getEvents(email, accessToken);
    }

    @GetMapping("/private")
    public String getPrivate() {
        return "Smth private";
    }
}
