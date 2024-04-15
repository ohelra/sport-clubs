package com.running.web.service.impl;

import com.running.web.dto.EventsDto;
import com.running.web.models.Club;
import com.running.web.models.Events;
import com.running.web.repository.ClubRepository;
import com.running.web.repository.EventsRepository;
import com.running.web.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.running.web.mapper.EventsMapper.mapToEvents;
import static com.running.web.mapper.EventsMapper.mapToEventsDto;

@Service
public class EventsServiceImpl implements EventsService {
    private EventsRepository eventsRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventsServiceImpl(EventsRepository eventsRepository, ClubRepository clubRepository) {
        this.eventsRepository = eventsRepository;
        this.clubRepository = clubRepository;
    }
    @Override
    public void createEvent(Long clubId, EventsDto eventsDto) {
        Club club = clubRepository.findById(clubId).get();
        Events events = mapToEvents(eventsDto);
        events.setClub(club);
        eventsRepository.save(events);
    }

    @Override
    public List<EventsDto> findAllEvents() {
        List<Events> events = eventsRepository.findAll();
        return events.stream().map(event -> mapToEventsDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventsDto findByEventId(Long eventId) {
        Events events = eventsRepository.findById(eventId).get();
        return mapToEventsDto(events);
    }

    @Override
    public void updateEvents(EventsDto eventsDto) {
        Events events = mapToEvents(eventsDto);
        eventsRepository.save(events);
    }

    @Override
    public void deleteEvents(long eventId) {
        eventsRepository.deleteById(eventId);
    }
}
