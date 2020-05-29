package pl.sda.javagda28.tourmanagingcrud.controller;


import org.apache.commons.io.IOUtils;

import org.junit.Before;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.sda.javagda28.tourmanagingcrud.dto.EventForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.service.AppUserDetailsService;
import pl.sda.javagda28.tourmanagingcrud.service.BandService;
import pl.sda.javagda28.tourmanagingcrud.service.EventService;
import pl.sda.javagda28.tourmanagingcrud.service.VenueService;
import pl.sda.javagda28.tourmanagingcrud.testdata.EventTestDataProvider;
import pl.sda.javagda28.tourmanagingcrud.testdata.VenueTestDataProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
class EventControllerTest {


    private static Event event1;
    private static Event event2;
    private static Venue venue1;
    private static Venue venue2;
    private static List<Event> events;


    @MockBean
    EventService eventService;

    @MockBean
    VenueService venueService;

    @MockBean
    BandService bandService;

//    @MockBean
//    BindingResult bindingResult;

    @MockBean
    AppUserDetailsService appUserDetailsService;

    @InjectMocks
    EventController eventController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init() {
        venue1 = VenueTestDataProvider.getVenue();
        venue2 = VenueTestDataProvider.getVenue();

        event1 = EventTestDataProvider.getEventBuilder()
                .venue(venue1)
                .build();

        event2 = EventTestDataProvider.getEventBuilder()
                .venue(venue2)
                .build();

        events = new ArrayList<>();
        events.add(event1);
        events.add(event2);


    }


    @Test
    void displaySpecificEventTest() throws Exception {

        when(eventService.findEventById(1L)).thenReturn(event1);

        mockMvc.perform(get("/events/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("event-info"));
    }

    @Test
    void displayEventsTest() throws Exception {

        when(eventService.getAllEvents()).thenReturn(events);

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("event-list"))
                .andExpect(model().attribute("events", hasSize(2)));

    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin", value = "admin")
    void displayEventFormTestWithAdminRoleGranted() throws Exception {
        mockMvc.perform(get("/events/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("event-form"));
    }

    @Test
    @WithMockUser(username = "band", roles = "BAND", password = "band", value = "band")
    void displayEventFormTestWithBandRoleGranted() throws Exception {
        mockMvc.perform(get("/events/add"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin", value = "admin")
    void deleteEventTestWithAdminRoleGranted() throws Exception {

        mockMvc.perform(post("/events/remove/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/events"));
    }

    @Test
    @WithMockUser(username = "band", roles = "BAND", password = "band", value = "band")
    void deleteEventTestWithBandRoleGranted() throws Exception {

        mockMvc.perform(post("/events/remove/{id}", 1L))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin", value = "admin")
    void addNewEventTestWithAdminRoleGranted() throws Exception {
        Venue ven1 = new Venue();
        ven1.setId(3L);

        Band band1 = new Band();
        band1.setId(1L);
        Band band2 = new Band();
        band2.setId(2L);
        List<Band> bands = List.of(band1, band2);

        List<Long> bandIds = List.of(1L, 2L);

        byte[] bytes = IOUtils.toByteArray(getClass().getResourceAsStream("/static/img/sonisphere.jpg"));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("multipart", "sonisphere", "text/plain", bytes);

        EventForm evForm1 = new EventForm("name", String.valueOf(LocalDateTime.now()), ven1.getId(), "lolo", bytes, bandIds);

        Event createdEvent = new Event(666L, evForm1.getName(), LocalDateTime.now(), evForm1.getBio(), null, bands, ven1);

        List<Event> events = new ArrayList<>();
        events.add(createdEvent);


//        when(eventService.createEvent(evForm1)).thenReturn(createdEvent);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart("/events/add")
                        .file(mockMultipartFile);

        this.mockMvc.perform(builder)
                .andExpect(status().isCreated());


//        mockMvc.perform(post("/events/add").content(bytes))
//                .andExpect(content().contentType(MediaType.ALL))
//                .andExpect(status().isCreated())
//                .andExpect(view().name("event-list"))
//                .andExpect(model().attribute("events", hasSize(1)));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin", value = "admin")
    void viewEditFormTestWithAdminRoleGranted() throws Exception {


        Event event = new Event();


        event.setId(1L);

        EventForm eventForm = new EventForm();

        eventForm.setName("lalal");
        eventForm.setDate(String.valueOf(LocalDateTime.now()));
        eventForm.setBio("lalalla");
        Venue venue = new Venue();
        venue.setId(1L);
        eventForm.setVenueId(venue.getId());
        Band band1 = new Band();
        Band band2 = new Band();
        band1.setId(2L);
        band2.setId(3L);
        List<Long> bandIds = List.of(2L, 3L);
        eventForm.setBandIds(bandIds);

        when(eventService.findEventById(1L)).thenReturn(event);

        mockMvc.perform(get("/events/edit/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("event-form"));
    }


}