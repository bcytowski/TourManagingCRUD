package pl.sda.javagda28.tourmanagingcrud.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.javagda28.tourmanagingcrud.dto.BandForm;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.testdata.BandFormTestDataProvider;
import pl.sda.javagda28.tourmanagingcrud.testdata.BandTestDataProvider;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class BandServiceTest {

    private static BandForm bandForm;

    @Autowired
    private BandService bandService;

    @BeforeAll
    public static void init(){
        bandForm = BandFormTestDataProvider.getBandForm();
    }

    @Test
    void getAllStartUpBands() {
        List<Band> result = bandService.getAllBands();

        assertEquals(4, result.size());
    }

    @Test
    void saveNewBandToDataBase(){
        bandService.createBand(bandForm);


    }

}