package pl.sda.javagda28.tourmanagingcrud.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
