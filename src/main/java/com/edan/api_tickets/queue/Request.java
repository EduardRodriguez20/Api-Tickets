package com.edan.api_tickets.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request {
    private String type;
    private Object object;
}
