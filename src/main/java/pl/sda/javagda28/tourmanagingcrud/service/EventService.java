package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.exceptions.TourManagingException;
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
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final BandRepository bandRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(final EventForm eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventDate = LocalDate.parse(eventForm.getDate(), formatter);

        Optional<Venue> venue = venueRepository.findById(eventForm.getVenueId());
        List<Band> bandsByIds = bandRepository.findByIdIn(eventForm.getBandIds());


        Event event = new Event(null, eventForm.getName(), LocalDateTime.of(eventDate, LocalTime.of(20, 0)), bandsByIds, venue.get());
        return eventRepository.saveAndFlush(event);
    }

    public void removeEvent(final Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public void updateEvent(final Long id, final EventForm eventForm) {
        Event event = eventRepository.findById(id)
                .map(e -> copyValuesFromFormToEvent(eventForm, e))
                .orElseThrow(() -> new IllegalArgumentException(" "));

        eventRepository.save(event);
    }

    private Event copyValuesFromFormToEvent(EventForm eventForm, Event eventFromDb) {
        eventFromDb.setName(eventForm.getName());
        eventFromDb.setDate(getStartDateTime(eventForm));
        eventFromDb.setBands(bandRepository.findByIdIn(eventForm.getBandIds()));
        eventFromDb.setVenue(venueRepository.findById(eventForm.getVenueId()).get());

        return eventFromDb;
    }

    private LocalDateTime getStartDateTime(EventForm eventForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventDate = LocalDate.parse(eventForm.getDate(), formatter);
        LocalTime localTime = LocalTime.of(20, 0);
        return LocalDateTime.of(eventDate, localTime);
    }

    public EventForm createEventFormById (final Long id){
        final Event event = eventRepository.findById(id)
                .orElseThrow(()-> new TourManagingException("couldn't find specific event"));


        EventForm eventForm = new EventForm();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        List<Long> bandIds = event.getBands().stream()
                .map(band -> band.getId())
                .collect(Collectors.toList());

        return eventForm.builder().name(event.getName()).date(event.getDate().format(formatter))
                .venueId(event.getVenue().getId()).bandIds(bandIds).build();
    }
}
