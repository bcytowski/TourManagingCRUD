package pl.sda.javagda28.tourmanagingcrud.testdata;

import pl.sda.javagda28.tourmanagingcrud.entity.Venue;

public class VenueTestDataProvider {

    public static Venue getVenue() {
        return Venue.builder()
                .id(1L)
                .name("testVenue")
                .address("testAddress")
                .bio("testBio")
                .build();
    }

    public static Venue.VenueBuilder getVenueBuilder() {
        return Venue.builder()
                .id(1L)
                .name("testVenue")
                .address("testAddress")
                .bio("testBio");
    }
}
