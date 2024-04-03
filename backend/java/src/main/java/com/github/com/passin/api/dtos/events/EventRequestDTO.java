package com.github.com.passin.api.dtos.events;

public record EventRequestDTO(
        String title ,
        String details ,
        Integer maximumAttendees
) {
}
