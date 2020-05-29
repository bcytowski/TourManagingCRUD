package pl.sda.javagda28.tourmanagingcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime date;

    @Column(length = 2000)
    private String bio;

    @Lob
    private byte [] eventPhoto;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Bands_Events",
            joinColumns = { @JoinColumn(name = "event_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "band_id", referencedColumnName = "id") }
    )
    private List<Band> bands;
    @ToString.Exclude
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "venue_id", nullable = true)
    private Venue venue;
}
