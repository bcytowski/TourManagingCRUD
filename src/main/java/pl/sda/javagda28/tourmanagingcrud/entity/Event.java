package pl.sda.javagda28.tourmanagingcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToMany(mappedBy = "events")
    private List<Band> bands;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}
