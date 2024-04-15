package com.running.web.service;

import com.running.web.dto.EventsDto;

import java.util.List;

public interface EventsService {
    void createEvent(Long clubId, EventsDto eventsDto);
    List<EventsDto> findAllEvents();

    EventsDto findByEventId(Long eventId);


    void updateEvents(EventsDto eventsDto);

    void deleteEvents(long eventId);
}
