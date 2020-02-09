package pl.sda.javagda28.tourmanagingcrud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.repository.BandRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.VenueRepository;

@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;

    @Override
    public void run(final String... args) throws Exception {
        venueRepository.save(new Venue(null, "Stadion Narodowy", "Warszawka", null));
        venueRepository.save(new Venue(null, "Stadion Energa", "Gdańsk", null));
        venueRepository.save(new Venue(null, "Atlas Arena", "Lódź", null));

        bandRepository.save(new Band(null, "Metallica", "thrash metal", 4L, null));
        bandRepository.save(new Band(null, "Anthrax", "thrash metal", 5L, null));
        bandRepository.save(new Band(null, "Slayer", "thrash metal", 4L, null));
        bandRepository.save(new Band(null, "Megadeth", "thrash metal", 4L, null));
    }
}
