package com.running.web.mapper;

import com.running.web.dto.ClubDto;
import com.running.web.models.Club;

import java.util.stream.Collectors;

import static com.running.web.mapper.EventsMapper.mapToEventsDto;

public class ClubMapper {
    public static Club mapToClub(ClubDto club) {
        Club clubDto = Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createOn(club.getCreateOn())
                .updateOn(club.getUpdateOn())
                .build();
        return clubDto;
    }

    public static ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createOn(club.getCreateOn())
                .updateOn(club.getUpdateOn())
                .events(club.getEvents().stream().map((events) -> mapToEventsDto(events)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }
}
