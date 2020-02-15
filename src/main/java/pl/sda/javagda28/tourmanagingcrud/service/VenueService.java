package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.dto.VenueForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.exceptions.TourManagingException;
import pl.sda.javagda28.tourmanagingcrud.repository.EventRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.VenueRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue createVenue(final VenueForm venueForm) {

        List<Event> byIdIn = eventRepository.findByIdIn(venueForm.getEventIds());

        Venue venue = new Venue(null, venueForm.getName(), venueForm.getAddress(), byIdIn);

        return venueRepository.save(venue);
    }

    public void removeVenue(final Long id) {
        Venue byId = venueRepository.findById(id).orElseThrow(() -> new TourManagingException("couldnt find specific venue"));



        venueRepository.deleteById(id);

    }


}
