package com.github.com.passin.api.controllers;

import com.github.com.passin.api.dtos.events.EventIdDTO;
import com.github.com.passin.api.dtos.events.EventRequestDTO;
import com.github.com.passin.api.dtos.events.EventResponseDTO;
import com.github.com.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventDetail(id));
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        final EventIdDTO eventCreated = eventService.createEvent(body);

        final URI uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventCreated.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventCreated);
    }
}
