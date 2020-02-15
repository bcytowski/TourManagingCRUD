package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;

import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.dto.EventForm;
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

    private static final String EVENTS_MODEL_ATTRIBUTE = "events";
    private static final String VENUES_MODEL_ATTRIBUTE = "venues";
    private static final String BANDS_MODEL_ATTRIBUTE = "bands";
    private static final String EVENT_FORM_MODEL_ATTRIBUTE = "eventForm";
    private static final String METHOD_MODEL_ATTRIBUTE = "method";
    private static final String ADD_METHOD_MODEL_ATTRIBUTE = "add";
    private static final String EDIT_METHOD_MODEL_ATTRIBUTE = "edit/";

    private static final String EVENT_LIST_TEMPLATE_PATH = "event-list";
    private static final String EVENT_FORM_TEMPLATE_PATH = "event-form";

    @GetMapping
    public String displayEvents(final ModelMap modelMap) {
        List<Event> events = eventService.getAllEvents();

        modelMap.addAttribute(EVENTS_MODEL_ATTRIBUTE, events);

        return EVENT_LIST_TEMPLATE_PATH;
    }
    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @GetMapping("/add")
    public String viewEventForm(final ModelMap modelMap) {
        List<Event> events = eventService.getAllEvents();
        List<Venue> venues = venueService.getAllVenues();
        List<Band> bands = bandService.getAllBands();
        modelMap.addAttribute(VENUES_MODEL_ATTRIBUTE, venues);
        modelMap.addAttribute(BANDS_MODEL_ATTRIBUTE, bands);
        modelMap.addAttribute(EVENT_FORM_MODEL_ATTRIBUTE, new EventForm());
        modelMap.addAttribute(EVENTS_MODEL_ATTRIBUTE, events);
        modelMap.addAttribute(METHOD_MODEL_ATTRIBUTE, ADD_METHOD_MODEL_ATTRIBUTE);

        return EVENT_FORM_TEMPLATE_PATH;
    }
    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @PostMapping("/add")
    public String saveEvent(@Valid @ModelAttribute final EventForm eventForm, final ModelMap modelMap) {
        eventService.createEvent(eventForm);
        return "redirect:/events";
    }
    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @PostMapping("/remove/{id}")
    public String removeEvent(@PathVariable("id") final Long id, final ModelMap modelMap) {
        eventService.removeEvent(id);
        return "redirect:/events";
    }
    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @GetMapping("/edit/{id}")
    public String viewEditForm(@PathVariable("id") final Long id, final ModelMap modelMap) {
        List<Venue> venues = venueService.getAllVenues();
        List<Band> bands = bandService.getAllBands();
        modelMap.addAttribute(VENUES_MODEL_ATTRIBUTE, venues);
        modelMap.addAttribute(BANDS_MODEL_ATTRIBUTE, bands);
        modelMap.addAttribute(EVENT_FORM_MODEL_ATTRIBUTE, eventService.createEventFormById(id));
        modelMap.addAttribute(METHOD_MODEL_ATTRIBUTE, EDIT_METHOD_MODEL_ATTRIBUTE + id);
        return EVENT_FORM_TEMPLATE_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @PostMapping("/edit/{id}")
    public String editEvent(@Valid @ModelAttribute final ModelMap modelMap, @PathVariable("id") final Long id, final EventForm eventForm) {
        eventService.updateEvent(id, eventForm);
        return "redirect:/events";
    }
}
