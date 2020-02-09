package pl.sda.javagda28.tourmanagingcrud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.repository.BandRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.EventRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.VenueRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;
    private final EventRepository eventRepository;

    @Override
    public void run(final String... args) throws Exception {
        Venue stadion = venueRepository.save(new Venue(null, "Stadion Narodowy", "Warszawka", null));
        venueRepository.save(new Venue(null, "Stadion Energa", "Gdańsk", null));
        venueRepository.save(new Venue(null, "Atlas Arena", "Lódź", null));

        Band metallica = bandRepository.save(new Band(null, "Metallica", "thrash metal", 4L, null));
        bandRepository.save(new Band(null, "Anthrax", "thrash metal", 5L, null));
        Band slayer = bandRepository.save(new Band(null, "Slayer", "thrash metal", 4L, null));
        bandRepository.save(new Band(null, "Megadeth", "thrash metal", 4L, null));

        eventRepository.save(new Event(null,"Dożynki", LocalDateTime.now(), Arrays.asList(metallica), stadion));
        eventRepository.save(new Event(null,"Kortowiada", LocalDateTime.now(),Arrays.asList(metallica, slayer), stadion));

    }
}
