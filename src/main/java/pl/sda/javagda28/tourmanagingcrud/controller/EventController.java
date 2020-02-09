package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;

import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.model.EventForm;
import pl.sda.javagda28.tourmanagingcrud.service.BandService;
import pl.sda.javagda28.tourmanagingcrud.service.EventService;
import pl.sda.javagda28.tourmanagingcrud.service.VenueService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final VenueService venueService;
    private final BandService bandService;

    @GetMapping("/")
    public String displayEvents(final ModelMap modelMap) {
        List<Event> events = eventService.getAllEvents();

        modelMap.addAttribute("events", events);

        return "event-list";
    }

    @GetMapping("/add")
    public String viewEventForm(final ModelMap modelMap) {
        List<Event> events = eventService.getAllEvents();
        List<Venue> venues = venueService.getAllVenues();
        List<Band> bands = bandService.getAllBands();
        modelMap.addAttribute("venues", venues);
        modelMap.addAttribute("bands", bands);
        modelMap.addAttribute("event", new EventForm());
        modelMap.addAttribute("events", events);
        return "event-form";
    }

    @PostMapping("/add")
    public String saveEvent(@Valid @ModelAttribute final EventForm eventForm, final ModelMap modelMap) {
        eventService.createEvent(eventForm);
        return displayEvents(modelMap);
    }
}
