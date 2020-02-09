package pl.sda.javagda28.tourmanagingcrud.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenueForm {

    @NotNull
    private String name;

    @NotNull
    private String address;

}
