package com.github.com.passin.services;

import com.github.com.passin.api.dtos.events.EventIdDTO;
import com.github.com.passin.api.dtos.events.EventRequestDTO;
import com.github.com.passin.api.dtos.events.EventResponseDTO;
import com.github.com.passin.domain.event.Event;
import com.github.com.passin.repositories.AttendeeRepository;
import com.github.com.passin.repositories.EventRepository;
import com.github.com.passin.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    public static final String RESOURCE_NOT_FOUND_WITH_ID = "Resource not found with id ";

    public EventResponseDTO getEventDetail(String eventId) {

        final int size = attendeeRepository.findByEventId(eventId).size();

        return eventRepository.findById(eventId)
                .map(event -> new EventResponseDTO(event , size))
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_WITH_ID + eventId));
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO) {
        final Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(createSlug(eventDTO.title()));

        eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        final String normalized = Normalizer.normalize(text , Normalizer.Form.NFC);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]" , "")
                .replaceAll("[^\\w\\s]" , "")
                .replaceAll("\\s+" , "-").toLowerCase();

    }

}
