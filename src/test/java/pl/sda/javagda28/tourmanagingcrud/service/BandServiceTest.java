package pl.sda.javagda28.tourmanagingcrud.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.javagda28.tourmanagingcrud.dto.BandForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.entity.Event;
import pl.sda.javagda28.tourmanagingcrud.exceptions.RecordNotFoundException;
import pl.sda.javagda28.tourmanagingcrud.repository.BandRepository;
import pl.sda.javagda28.tourmanagingcrud.testdata.BandFormTestDataProvider;
import pl.sda.javagda28.tourmanagingcrud.testdata.BandTestDataProvider;
import pl.sda.javagda28.tourmanagingcrud.testdata.EventTestDataProvider;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BandServiceTest {

    private static BandForm bandForm;
    private static Band band1;
    private static Event event1;
    private static Event event2;

    @Autowired
    private BandService bandService;

    @Autowired
    private BandRepository bandRepository;

    @BeforeAll
    public static void init() {
        bandForm = BandFormTestDataProvider.getBandForm();
        band1 = BandTestDataProvider.getBand();
        event1 = EventTestDataProvider.getEvent();
        event2 = EventTestDataProvider.getEvent();
        List<Long> eventIds = List.of(event1.getId(), event2.getId());
        bandForm.setEventIds(eventIds);
    }

    @Test
    void getAllStartUpBands() {
        List<Band> result = bandService.getAllBands();

        assertEquals(4, result.size());
    }

    @Test
    void saveNewBandToDatabase() {
        bandService.createBand(bandForm);

        Band result = bandRepository.findBandByName(bandForm.getName());

        assertEquals(bandForm.getName(), result.getName());
    }

    @Test
    void removeBandFromDatabase() {
        bandService.createBand(bandForm);
        long countBeforeRemoving = bandRepository.count();
        Band createdBand = bandRepository.findBandByName(bandForm.getName());
        Long id = createdBand.getId();


        bandService.removeBand(id);
        long countAfterRemoving = bandRepository.count();

        assertEquals(1, countBeforeRemoving - countAfterRemoving);
    }

    @Test
    void updateBand() {
        bandService.createBand(bandForm);
        Band createdBand = bandRepository.findBandByName(bandForm.getName());
        Long idOfCreatedBand = createdBand.getId();

        bandForm.setName("otherTestName");
        bandService.updateBand(idOfCreatedBand, bandForm);
        Optional<Band> byId = Optional.ofNullable(bandRepository.findById(idOfCreatedBand)
                .orElseThrow(() -> new RecordNotFoundException("couldn't find specific band")));

        assertEquals("otherTestName", byId.get().getName() );
    }


}