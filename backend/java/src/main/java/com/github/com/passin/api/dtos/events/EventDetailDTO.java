package com.github.com.passin.api.dtos.events;

public record EventDetailDTO(
        String id ,
        String title ,
        String details ,
        String slug ,
        Integer maximumAttendees ,
        Integer attendeesAmount
) {
}
