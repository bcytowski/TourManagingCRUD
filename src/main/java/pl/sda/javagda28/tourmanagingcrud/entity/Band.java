package pl.sda.javagda28.tourmanagingcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
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

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "Bands_Events",
//            joinColumns = { @JoinColumn(name = "band_id", referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "event_id", referencedColumnName = "id") }
//    )
    @ManyToMany(mappedBy = "bands")
    private List<Event> events;

}
