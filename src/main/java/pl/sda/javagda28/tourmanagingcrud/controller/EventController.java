package pl.sda.javagda28.tourmanagingcrud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.service.EventService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public String displayEvents(final ModelMap modelMap){
        List<Event> events = eventService.getAllEvents();
        modelMap.addAttribute("events", events);
        return "events";
    }

//    @PostMapping(path = "/add")
//    public String saveUser(@Valid @ModelAttribute final EventForm eventForm, final ModelMap modelMap) {
//        userService.createUser(userForm);
//        return displayUsers(modelMap);
//    }
}
