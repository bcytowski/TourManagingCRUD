package pl.sda.javagda28.tourmanagingcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javagda28.tourmanagingcrud.entity.Band;
import pl.sda.javagda28.tourmanagingcrud.repository.BandRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BandService {

    private final BandRepository bandRepository;

    public List<Band> getAllBands(){
        return bandRepository.findAll();
    }
}
