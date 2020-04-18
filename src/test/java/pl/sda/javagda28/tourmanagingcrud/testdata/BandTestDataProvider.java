package pl.sda.javagda28.tourmanagingcrud.testdata;

import pl.sda.javagda28.tourmanagingcrud.entity.Band;

public class BandTestDataProvider {
    public static Band getBand() {
        return Band.builder()
                .id(1L)
                .name("testBand")
                .members(4L)
                .bio("testBio")
                .build();
    }

    public static Band.BandBuilder getBandBuilder(){
        return Band.builder()
                .id(1L)
                .name("testBand")
                .members(4L)
                .bio("testBio");
    }
}
