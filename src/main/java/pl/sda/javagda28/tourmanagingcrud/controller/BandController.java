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
import pl.sda.javagda28.tourmanagingcrud.dto.BandForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.service.BandService;
import pl.sda.javagda28.tourmanagingcrud.service.EventService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bands")
@RequiredArgsConstructor
public class BandController {

    private static final String BAND_MODEL_ATTRIBUTE = "band";
    private static final String BAND_LIST_MODEL_ATTRIBUTE = "bands";
    private static final String BAND_FORM_MODEL_ATTRIBUTE = "bandForm";
    private static final String EVENTS_MODEL_ATTRIBUTE = "events";
    private static final String METHOD_MODEL_ATTRIBUTE = "method";
    private static final String ADD_METHOD_MODEL_ATTRIBUTE = "add";
    private static final String EDIT_METHOD_MODEL_ATTRIBUTE = "edit/";

    private static final String BAND_LIST_TEMPLATE_PATH = "band-list";
    private static final String BAND_FORM_TEMPLATE_PATH = "band-form";
    private static final String BAND_INFO_TEMPLATE_PATH = "band-info";


    private final BandService bandService;
    private final EventService eventService;


    @GetMapping
    public String displayBands(final ModelMap modelMap) {
        List<Band> allBands = bandService.getAllBands();
        modelMap.addAttribute(BAND_LIST_MODEL_ATTRIBUTE, allBands);
        return BAND_LIST_TEMPLATE_PATH;
    }

    @GetMapping("/{id}")
    public String viewSpecificBand(@PathVariable("id") final Long id, final ModelMap modelMap) {
        Band bandById = bandService.findBandById(id);
        modelMap.addAttribute(BAND_MODEL_ATTRIBUTE, bandById);
        return BAND_INFO_TEMPLATE_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER", "ROLE_BAND"})
    @GetMapping("/add")
    public String viewBandForm(final ModelMap modelMap) {
        List<Band> allBands = bandService.getAllBands();

        modelMap.addAttribute(BAND_LIST_MODEL_ATTRIBUTE, allBands);
        modelMap.addAttribute(BAND_FORM_MODEL_ATTRIBUTE, new BandForm());
        modelMap.addAttribute(METHOD_MODEL_ATTRIBUTE, ADD_METHOD_MODEL_ATTRIBUTE);
        return BAND_FORM_TEMPLATE_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER", "ROLE-BAND"})
    @PostMapping("/add")
    public String createVenue(@Valid @ModelAttribute final BandForm bandForm, final ModelMap modelMap) {
        bandService.createBand(bandForm);
        return "redirect:/bands";
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER", "ROLE-BAND"})
    @PostMapping("/remove/{id}")
    public String removeEvent(@PathVariable("id") final Long id, final ModelMap modelMap) {
        bandService.removeBand(id);
        return "redirect:/bands";
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER", "ROLE-BAND"})
    @GetMapping("/edit/{id}")
    public String viewEditForm(@PathVariable("id") final Long id, final ModelMap modelMap) {

        List<Event> allEvents = eventService.getAllEvents();
        List<Band> allBands = bandService.getAllBands();
        modelMap.addAttribute(EVENTS_MODEL_ATTRIBUTE, allEvents);
        modelMap.addAttribute(BAND_LIST_MODEL_ATTRIBUTE, allBands);
        modelMap.addAttribute(BAND_FORM_MODEL_ATTRIBUTE, bandService.createBandFormById(id));
        modelMap.addAttribute(METHOD_MODEL_ATTRIBUTE, EDIT_METHOD_MODEL_ATTRIBUTE + id);
        return BAND_FORM_TEMPLATE_PATH;
    }

    @Secured({"ROLE_ADMIN", "ROLE_ORGANISER", "ROLE-BAND"})
    @PostMapping("/edit/{id}")
    public String editBand(@Valid @ModelAttribute final ModelMap modelMap,
                           @PathVariable("id") final Long id, final BandForm bandForm) {
        bandService.updateBand(id, bandForm);
        return "redirect:/bands";
    }


}
