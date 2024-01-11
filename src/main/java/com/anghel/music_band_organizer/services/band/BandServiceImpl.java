package com.anghel.music_band_organizer.services.band;

import com.anghel.music_band_organizer.models.dtos.BandDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.repository.BandRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BandServiceImpl implements BandService{

    private final BandRepository bandRepository;
    private final BandServiceValidation bandServiceValidation;
    private final ModelMapper modelMapper;

    public BandServiceImpl(BandRepository bandRepository, BandServiceValidation bandServiceValidation, ModelMapper modelMapper) {
        this.bandRepository = bandRepository;
        this.bandServiceValidation = bandServiceValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public BandDTO createBand(BandDTO bandDTO) {
        bandServiceValidation.validateBandAlreadyExists(bandDTO);

        Band band = modelMapper.map(bandDTO, Band.class);
        Band savedBand = bandRepository.save(band);
        log.info("Band with id {} inserted in db. Method: {}", savedBand.getId(), "createBand");

        return modelMapper.map(savedBand, BandDTO.class);
    }

    @Override
    public List<BandDTO> getAllBands() {
        List<Band> bandList = bandRepository.findAll();
        log.info("Band list retrieved. Method: {}", "getAllBands");

        return bandList.stream()
                .map(band -> modelMapper.map(band, BandDTO.class))
                .toList();
    }

    @Override
    public BandDTO getBandById(Long bandId) {
        Band band = bandServiceValidation.getValidBand(bandId, "getBandById");

        return modelMapper.map(band, BandDTO.class);
    }

    @Override
    public String deleteBandById(Long bandId) {
        bandServiceValidation.getValidBand(bandId, "deleteBandById");

        bandRepository.deleteById(bandId);
        log.info("Band with id {} deleted. Method: {}", bandId, "deleteBandById");

        return "Band with id " + bandId + " deleted.";
    }
}
