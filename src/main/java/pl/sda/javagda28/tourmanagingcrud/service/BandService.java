package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.dto.BandForm;
import pl.sda.javagda28.tourmanagingcrud.dto.EventForm;
import pl.sda.javagda28.tourmanagingcrud.dto.VenueForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.exceptions.TourManagingException;
import pl.sda.javagda28.tourmanagingcrud.repository.BandRepository;
import pl.sda.javagda28.tourmanagingcrud.repository.EventRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BandService {

    private final BandRepository bandRepository;
    private final EventRepository eventRepository;

    public List<Band> getAllBands(){
        return bandRepository.findAll();
    }

    public void createBand(final BandForm bandForm) {
        List<Event> byIdIn = eventRepository.findByIdIn(bandForm.getEventIds());

        String actualValueOfYouTubeLink = getActualValueOfYouTubeLink(bandForm);
        Band band = new Band (null, bandForm.getName(), bandForm.getMusicGenre(), bandForm.getMembers(), bandForm.getBio(), actualValueOfYouTubeLink, byIdIn);
        bandRepository.save(band);
    }

    private String getActualValueOfYouTubeLink(final BandForm bandForm) {
        String youTubeLink = bandForm.getYouTubeLink();
        String[] splitLink = youTubeLink.split("=");
        return splitLink[1];
    }

    public void removeBand(final Long id) {
        bandRepository.deleteById(id);
    }

    public void updateBand(final Long id, final BandForm bandForm) {
        Band band = bandRepository.findById(id)
                .map(b -> copyValuesFromFormToBand(bandForm, b))
                .orElseThrow(() -> new IllegalArgumentException(" "));

       bandRepository.save(band);
    }

    private Band copyValuesFromFormToBand(final BandForm bandForm, final Band bandFromDB) {
        String actualValueOfYouTubeLink = getActualValueOfYouTubeLink(bandForm);

        bandFromDB.setName(bandForm.getName());
        bandFromDB.setMusicGenre(bandForm.getMusicGenre());
        bandFromDB.setMembers(bandForm.getMembers());
        bandFromDB.setBio(bandForm.getBio());
        bandFromDB.setYouTubeLink(actualValueOfYouTubeLink);
        bandFromDB.setEvents(eventRepository.findByIdIn(bandForm.getEventIds()));

        return bandFromDB;
    }
    public BandForm createBandFormById (final Long id){
        final Band band = bandRepository.findById(id)
                .orElseThrow(()-> new TourManagingException("couldn't find specific band"));

        String valueOfYouTubeLink = band.getYouTubeLink();
        String youTubeLinkWithoutValue = "https://www.youtube.com/watch?v=";
        String actualYouTubeLink = youTubeLinkWithoutValue.concat(valueOfYouTubeLink);

        BandForm bandForm = new BandForm();

        List<Long> eventIds = band.getEvents().stream()
                .map(event -> band.getId())
                .collect(Collectors.toList());

        return bandForm.builder().name(band.getName()).musicGenre(band.getMusicGenre())
                .members(band.getMembers()).bio(band.getBio()).youTubeLink(actualYouTubeLink).eventIds(eventIds).build();
    }

    public Band findBandById(final Long id){
       Band band = bandRepository.findById(id)
                .orElseThrow(()-> new TourManagingException("couldn't find specific band"));
        return band;
    }



}
