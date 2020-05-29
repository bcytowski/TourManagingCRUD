package pl.sda.javagda28.tourmanagingcrud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "venues")
public class Venue {
    @Id
    @GeneratedValue
    private Long id;


    private String name;

    private String address;

    @Column(length = 2000)
    private String bio;

    @Lob
    private byte[] venueImage;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Event> events;
}
