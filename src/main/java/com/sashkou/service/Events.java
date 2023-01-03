package com.sashkou.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Events {
    private String email;
    private List<String> events;
}
