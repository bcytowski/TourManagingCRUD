package pl.sda.javagda28.tourmanagingcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "bands")
public class Band {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Column(name = "music_genre")
    private String musicGenre;

    private Long members;

    @Column(length = 2000)
    private String bio;

    private String youTubeLink;

    @Lob
    private byte [] bandPhoto;

    @ToString.Exclude
    @ManyToMany(mappedBy = "bands")
    private List<Event> events;

}
