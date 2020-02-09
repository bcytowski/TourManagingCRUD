package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.model.EventForm;
import pl.sda.javagda28.tourmanagingcrud.repository.BandRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.EventRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.VenueRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;

    public Event createEvent(final EventForm eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventDate = LocalDate.parse(eventForm.getDate(), formatter);

        Optional<Venue> venue = venueRepository.findById(eventForm.getVenueId());
        List<Band> bandsByIds = bandRepository.findByIdIn(eventForm.getBandIds());


        Event event = new Event(null, eventForm.getName(), LocalDateTime.of(eventDate, LocalTime.of(20, 0)), bandsByIds, venue.get());
        return eventRepository.saveAndFlush(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
