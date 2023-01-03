package com.sashkou.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Events;
import com.sashkou.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

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
}
