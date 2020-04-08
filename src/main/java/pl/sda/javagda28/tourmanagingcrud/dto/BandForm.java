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
    private String name;

    @NotNull
    private String musicGenre;

    @NotNull
    private Long members;


    private String bio;

    private String youTubeLink;

    private byte[] bandPhoto;

    private List<Long> eventIds = new ArrayList<>();
}
