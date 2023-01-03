package com.sashkou.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Events;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventsService {

    public com.sashkou.service.Events getEvents(String email, String token) throws Exception {
        GoogleCredential credential = new GoogleCredential();
        credential.setAccessToken(token);

        Calendar service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), new JacksonFactory(), credential)
                .setApplicationName("Anton's Application")
                .build();

        Events calendarEvents = service.events()
                .list("primary")
                .setMaxResults(10)
                .setTimeMin(new DateTime(System.currentTimeMillis()))
                .execute();

        List<String> events = calendarEvents.getItems().stream()
                .map(item -> (String) item.get("summary"))
                .collect(Collectors.toList());

        return new com.sashkou.service.Events(email, events);
    }

}
