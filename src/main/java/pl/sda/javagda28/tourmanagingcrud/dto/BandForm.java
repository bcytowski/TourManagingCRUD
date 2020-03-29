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
public class BandForm {
    @NotNull
    public String name;

    @NotNull
    public String musicGenre;

    @NotNull
    public Long members;


    public String bio;

    public String youTubeLink;

    List<Long> eventIds = new ArrayList<>();
}
