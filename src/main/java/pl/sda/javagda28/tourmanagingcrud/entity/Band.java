package pl.sda.javagda28.tourmanagingcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

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

    @ToString.Exclude
    @ManyToMany(mappedBy = "bands")
    private List<Event> events;

}
