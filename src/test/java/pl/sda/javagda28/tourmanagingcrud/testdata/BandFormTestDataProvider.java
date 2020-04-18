package pl.sda.javagda28.tourmanagingcrud.testdata;

import pl.sda.javagda28.tourmanagingcrud.dto.BandForm;

public class BandFormTestDataProvider {

    public static BandForm getBandForm() {
        return BandForm.builder()
                .name("testBand")
                .members(4L)
                .bio("testBio")
                .build();
    }

    public static BandForm.BandFormBuilder getBandFormBuilder(){
        return BandForm.builder()
                .name("testBand")
                .members(4L)
                .bio("testBio");
    }

}
