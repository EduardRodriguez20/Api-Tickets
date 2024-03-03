package com.edan.api_tickets.controller;

import org.springframework.http.ResponseEntity;

public interface BaseControllerDocument<T>{
    ResponseEntity<T> getByDocument(Long document);
}
