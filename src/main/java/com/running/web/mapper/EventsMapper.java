package com.running.web.mapper;

import com.running.web.dto.EventsDto;
import com.running.web.models.Events;

public class EventsMapper {
    public static Events mapToEvents(EventsDto eventsDto){
        return Events.builder()
                .id(eventsDto.getId())
                .name(eventsDto.getName())
                .startTime(eventsDto.getStartTime())
                .endTime(eventsDto.getEndTime())
                .type(eventsDto.getType())
                .photoUrl(eventsDto.getPhotoUrl())
                .createOn(eventsDto.getCreateOn())
                .updateOn(eventsDto.getUpdateOn())
                .club(eventsDto.getClub())
                .build();
    }

    public static EventsDto mapToEventsDto(Events events){
        return EventsDto.builder()
                .id(events.getId())
                .name(events.getName())
                .startTime(events.getStartTime())
                .endTime(events.getEndTime())
                .type(events.getType())
                .photoUrl(events.getPhotoUrl())
                .createOn(events.getCreateOn())
                .updateOn(events.getUpdateOn())
                .club(events.getClub())
                .build();
    }
}
