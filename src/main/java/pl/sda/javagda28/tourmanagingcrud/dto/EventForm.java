package pl.sda.javagda28.tourmanagingcrud.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventForm {

    @NotNull
    @Length(min = 2)
    private String name;

    @NotNull
    private String date;

    @NotNull
    private Long venueId;

    private List<Long> bandIds = new ArrayList<Long>();

}
