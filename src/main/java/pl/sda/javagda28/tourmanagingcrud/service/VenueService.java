package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.entity.Venue;
import pl.sda.javagda28.tourmanagingcrud.repository.VenueRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public List<Venue> getAllVenues(){
        return venueRepository.findAll();
    }

    public Venue createVenue(final Venue venue){
        return venueRepository.save(venue);
    }


}
