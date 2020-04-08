package pl.sda.javagda28.tourmanagingcrud.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueForm {

    @NotNull
    private String name;

    @NotNull
    private String address;

    private String bio;

    private byte[] venueImage;

    List<Long> eventIds = new ArrayList<>();

}
