package pl.sda.javagda28.tourmanagingcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @Column(name = "name")
    private String name;
    @Column(name = "music_genre")
    private String musicGenre;
    @Column(name = "members")
    private Long members;

    @ManyToMany(mappedBy = "bands")
    private List<Event> events;

}
