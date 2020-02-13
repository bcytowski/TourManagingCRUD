package pl.sda.javagda28.tourmanagingcrud;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.javagda28.tourmanagingcrud.entity.*;
import pl.sda.javagda28.tourmanagingcrud.repository.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartupRunner implements CommandLineRunner {

    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;
    private final EventRepository eventRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

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

        final Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        final Role roleBand = new Role("ROLE_BAND");
        roleRepository.save(roleBand);

        final Role roleOrganiser = new Role("ROLE_ORGANISER");
        roleRepository.save(roleOrganiser);

        final User user = new User(null,"admin", "admin@admins.com",
                passwordEncoder.encode("admin"), List.of(),
                List.of(roleAdmin));
        userRepository.save(user);

        final User bandUser = new User(null,"band", "band@band.com",
                passwordEncoder.encode("band"),List.of(), List.of(roleBand));
        userRepository.save(bandUser);

        final User organiserUser = new User(null, "organiser", "organiser@organiser.com",
                passwordEncoder.encode("organiser"), List.of(), List.of(roleOrganiser));
        userRepository.save(organiserUser);
    }
}
