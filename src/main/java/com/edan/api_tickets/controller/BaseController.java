package com.edan.api_tickets.controller;

import com.edan.api_tickets.repository.entities.Answer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseController<T> {
    ResponseEntity<List<T>> getAll();
    ResponseEntity<T> getById(Integer id);
    ResponseEntity<Answer> create(T entity);
//    ResponseEntity<T> update(Long id, T entity);
    ResponseEntity<Answer> delete(Long id);
}
