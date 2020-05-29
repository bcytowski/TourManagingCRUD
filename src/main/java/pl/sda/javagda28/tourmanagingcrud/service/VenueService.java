package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.dto.VenueForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.exceptions.RecordNotFoundException;
import pl.sda.javagda28.tourmanagingcrud.exceptions.NotEmptyException;
import pl.sda.javagda28.tourmanagingcrud.repository.EventRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.VenueRepository;

import java.util.List;
import java.util.stream.Collectors;

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

        Venue venue = new Venue(null, venueForm.getName(),
                venueForm.getAddress(), venueForm.getBio(), venueForm.getVenueImage(), byIdIn);

        return venueRepository.save(venue);
    }

    public void removeVenue(final Long id) {
        Venue byId = venueRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("couldn't find specific venue"));
        if (byId.getEvents().size() == 0) {
            venueRepository.deleteById(id);
        } else {
            throw new NotEmptyException("venue is not empty");
        }
    }

    public void updateVenue(final Long id, final VenueForm venueForm) {
        Venue venue = venueRepository.findById(id)
                .map(ven -> copyValuesFromFormToVenue(venueForm, ven))
                .orElseThrow(() -> new RecordNotFoundException("couldn't find specific venue"));

        venueRepository.save(venue);
    }

    public Venue copyValuesFromFormToVenue(final VenueForm venueForm, final Venue venueFromDB) {
        venueFromDB.setName(venueForm.getName());
        venueFromDB.setAddress(venueForm.getAddress());
        venueFromDB.setBio(venueForm.getBio());
        venueFromDB.setVenueImage(venueForm.getVenueImage());
        venueFromDB.setEvents(eventRepository.findByIdIn(venueForm.getEventIds()));

        return venueFromDB;
    }


    public VenueForm createVenueFormById(final Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("couldn't find specific venue"));

        VenueForm venueForm = new VenueForm();

        List<Long> eventIds = venue.getEvents().stream()
                .map(event -> event.getId())
                .collect(Collectors.toList());

        return venueForm.builder().name(venue.getName())
                .address(venue.getAddress()).bio(venue.getBio()).venueImage(venue.getVenueImage()).eventIds(eventIds).build();
    }

    public Venue findVenueById(final Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("couldn't find specific venue"));
    }
}
