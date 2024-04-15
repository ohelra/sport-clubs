package com.running.web.controller;

import com.running.web.dto.EventsDto;
import com.running.web.models.Events;
import com.running.web.service.EventsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventsController {
    private EventsService eventsService;
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping("/events")
    public String eventsList(Model model){
        List<EventsDto> events = eventsService.findAllEvents();
        model.addAttribute("events",events);
        return "events-list";
    }

    @GetMapping("/events/{eventsId}")
    public String viewEvent(@PathVariable("eventsId") Long eventId, Model model){
        EventsDto eventsDto = eventsService.findByEventId(eventId);
        model.addAttribute("events",eventsDto);
        return "events-detail";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventsForm(@PathVariable("clubId") Long clubId, Model model){
        Events events = new Events();
        model.addAttribute("clubId",clubId);
        model.addAttribute("events",events);
        return "events-create";
    }

    @GetMapping("/events/{eventsId}/edit")
    public String editEventsForm(@PathVariable("eventsId") Long eventId, Model model){
        EventsDto events = eventsService.findByEventId(eventId);
        model.addAttribute("events",events);
        return "events-edit";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("events") EventsDto eventsDto, BindingResult result ,Model model){
        if(result.hasErrors()){
            model.addAttribute("events",eventsDto);
            return "club-create";
        }
        eventsService.createEvent(clubId, eventsDto);
        return "redirect:/clubs/" + clubId;
    }

    @PostMapping("/events/{eventsId}/edit")
    public String updateEvents(@PathVariable("eventsId") Long eventId, @Valid @ModelAttribute("events") EventsDto events, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("events", events);
            return "events-edit";
        }
        EventsDto eventsDto = eventsService.findByEventId(eventId);
        events.setId(eventId);
        events.setClub(eventsDto.getClub());
        eventsService.updateEvents(events);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventsId}/delete")
    public String deleteEvents(@PathVariable("eventsId") long eventId){
        eventsService.deleteEvents(eventId);
        return "redirect:/events";
    }
}
