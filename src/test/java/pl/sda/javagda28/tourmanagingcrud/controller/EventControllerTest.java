package pl.sda.javagda28.tourmanagingcrud.controller;


import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.service.AppUserDetailsService;
import pl.sda.javagda28.tourmanagingcrud.service.BandService;
import pl.sda.javagda28.tourmanagingcrud.service.EventService;
import pl.sda.javagda28.tourmanagingcrud.service.VenueService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
class EventControllerTest {

    @MockBean
    EventService eventService;

    @MockBean
    VenueService venueService;

    @MockBean
    BandService bandService;

    @MockBean
    AppUserDetailsService appUserDetailsService;

    @InjectMocks
    EventController eventController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    void testList() throws Exception {
        List<Event> events = new ArrayList<>();
        Venue venue1 = new Venue();
        venue1.setName("ven1");
        Venue venue2= new Venue();
        venue2.setName("ven2");
        Event event1 = new Event();
        Event event2= new Event();
        event1.setVenue(venue1);
        event2.setVenue(venue2);
        events.add(event1);
        events.add(event2);

        when(eventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("event-list"))
                .andExpect(model().attribute("events", hasSize(2)));

    }

}