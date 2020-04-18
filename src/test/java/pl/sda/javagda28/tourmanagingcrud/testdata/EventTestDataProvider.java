package pl.sda.javagda28.tourmanagingcrud.testdata;

import pl.sda.javagda28.tourmanagingcrud.entity.Event;

import java.time.LocalDateTime;

public class EventTestDataProvider {

    public static Event getEvent() {
        return Event.builder()
                .id(1L)
                .name("testEvent")
                .date(LocalDateTime.now())
                .bio("testBio")
                .build();
    }

    public static Event.EventBuilder getEventBuilder() {
        return Event.builder()
                .id(1L)
                .name("testEvent")
                .date(LocalDateTime.now())
                .bio("testBio");
    }
}
