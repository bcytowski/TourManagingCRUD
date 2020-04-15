package pl.sda.javagda28.tourmanagingcrud.controller;


import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
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
import pl.sda.javagda28.tourmanagingcrud.testdata.EventTestDataProvider;
import pl.sda.javagda28.tourmanagingcrud.testdata.VenueTestDataProvider;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Before
    public void setUp() {
        Venue venue1 = VenueTestDataProvider.getVenue();
        Venue venue2 = VenueTestDataProvider.getVenue();

        Event event1 = EventTestDataProvider.getEventBuilder()
                .venue(venue1)
                .build();

        Event event2 = EventTestDataProvider.getEventBuilder()
                .venue(venue2)
                .build();

        when(eventService.getAllEvents()).thenReturn(List.of(event1, event2));
    }

    @Test
    void testList() throws Exception {
        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("event-list"))
                .andExpect(model().attribute("events", hasSize(2)));
    }

}
