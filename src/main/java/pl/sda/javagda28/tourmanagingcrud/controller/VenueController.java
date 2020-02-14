package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.javagda28.tourmanagingcrud.dto.EventForm;
import pl.sda.javagda28.tourmanagingcrud.dto.VenueForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.service.VenueService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private static final String MODEL_VENUE_FORM = "venueForm";
    private static final String MODEL_VENUES_ATTRIBUTE = "venues";
    private static final String VENUE_LIST_PATH = "venue-list";
    private static final String VENUE_FORM_PATH = "venue-form";

    private final VenueService venueService;

    @GetMapping
    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    public String displayVenues(final ModelMap modelMap){
        List<Venue> allVenues = venueService.getAllVenues();

        modelMap.addAttribute(MODEL_VENUES_ATTRIBUTE, allVenues);
        return VENUE_LIST_PATH;
    }
    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @GetMapping("/add")
    public String viewVenueForm(final ModelMap modelMap){
        List<Venue> allVenues = venueService.getAllVenues();

        modelMap.addAttribute(MODEL_VENUES_ATTRIBUTE, allVenues);
        modelMap.addAttribute(MODEL_VENUE_FORM, new VenueForm());
        return VENUE_FORM_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER"})
    @PostMapping("/add")
    public String saveVenue(@Valid @ModelAttribute final VenueForm venueForm, final ModelMap modelMap) {
        venueService.createVenue(venueForm);
        return displayVenues(modelMap);
    }


}
