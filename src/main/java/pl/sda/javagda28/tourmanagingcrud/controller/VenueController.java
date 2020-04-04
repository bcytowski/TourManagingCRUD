package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.javagda28.tourmanagingcrud.dto.VenueForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.service.EventService;
import pl.sda.javagda28.tourmanagingcrud.service.VenueService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private static final String VENUE_MODEL_ATTRIBUTE = "venue";
    private static final String MODEL_VENUE_FORM = "venueForm";
    private static final String MODEL_VENUES_ATTRIBUTE = "venues";
    private static final String METHOD_MODEL_ATTRIBUTE = "method";
    private static final String ADD_METHOD_MODEL_ATTRIBUTE = "add";
    private static final String EDIT_METHOD_MODEL_ATTRIBUTE = "edit/";

    private static final String VENUE_LIST_PATH = "venue-list";
    private static final String VENUE_FORM_TEMPLATE_PATH = "venue-form";
    private static final String VENUE_INFO_TEMPLATE_PATH = "venue-info";


    private final VenueService venueService;
    private final EventService eventService;

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    public String displayVenues(final ModelMap modelMap) {
        List<Venue> allVenues = venueService.getAllVenues();

        modelMap.addAttribute(MODEL_VENUES_ATTRIBUTE, allVenues);
        return VENUE_LIST_PATH;
    }

    @GetMapping("/{id}")
    public String viewSpecificVenue(@PathVariable("id") final Long id, final ModelMap modelMap) {
        Venue venueById = venueService.findVenueById(id);
        modelMap.addAttribute(VENUE_MODEL_ATTRIBUTE, venueById);
        return VENUE_INFO_TEMPLATE_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @GetMapping("/add")
    public String viewVenueForm(final ModelMap modelMap) {
        List<Venue> allVenues = venueService.getAllVenues();

        modelMap.addAttribute(MODEL_VENUES_ATTRIBUTE, allVenues);
        modelMap.addAttribute(MODEL_VENUE_FORM, new VenueForm());
        modelMap.addAttribute(METHOD_MODEL_ATTRIBUTE, ADD_METHOD_MODEL_ATTRIBUTE);
        return VENUE_FORM_TEMPLATE_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @PostMapping("/add")
    public String createVenue(@Valid @ModelAttribute final VenueForm venueForm, final ModelMap modelMap) {
        venueService.createVenue(venueForm);
        return displayVenues(modelMap);
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @PostMapping("/remove/{id}")
    public String removeVenue(@PathVariable final Long id, final ModelMap modelMap) {
        venueService.removeVenue(id);
        return displayVenues(modelMap);
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @GetMapping("/edit/{id}")
    public String viewEditForm(@PathVariable final Long id, final ModelMap modelMap) {
        List<Event> allEvents = eventService.getAllEvents();
        modelMap.addAttribute("events", allEvents);

        modelMap.addAttribute(METHOD_MODEL_ATTRIBUTE, EDIT_METHOD_MODEL_ATTRIBUTE + id);
        modelMap.addAttribute(MODEL_VENUE_FORM, venueService.createVenueFormById(id));

        return VENUE_FORM_TEMPLATE_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @PostMapping("/edit/{id}")
    public String editVenue(@PathVariable final Long id,
                            @Valid @ModelAttribute final ModelMap modelMap, final VenueForm venueForm) {
        venueService.updateVenue(id, venueForm);
        return "redirect:/venues"; //displayVenues(modelMap);
    }


}
