package com.example.seasavior.service;

import com.example.seasavior.model.Observation;
import com.example.seasavior.repository.ObservationRepository;
import com.example.seasavior.exception.ObservationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    public Observation save(Observation observation) {
        return observationRepository.save(observation);
    }

    public List<Observation> findAll() {
        return observationRepository.findAll();
    }

    public Optional<Observation> findById(Long id) {
        return observationRepository.findById(id);
    }

    public void deleteById(Long id) {
        observationRepository.deleteById(id);
    }

    public Observation update(Observation observation) {
        if (observationRepository.existsById(observation.getId())) {
            return observationRepository.save(observation);
        } else {
            throw new ObservationNotFoundException(observation.getId());
        }
    }

    public String calculateWaterQuality(double salinity, double pH, double temperature) {
        boolean isSalinityIdeal = salinity >= 15 && salinity <= 30; // Salinidade ideal entre 15 e 30 ppt (partes por mil)
        boolean isPHNeutral = pH >= 6.5 && pH <= 8.5; // pH neutro entre 6.5 e 8.5
        boolean isTemperatureOptimal = temperature >= 20 && temperature <= 25; // Temperatura ideal entre 20°C e 25°C

        if (isSalinityIdeal && isPHNeutral && isTemperatureOptimal) {
            return "Excelente";
        } else if (isSalinityIdeal && isPHNeutral) {
            return "Boa";
        } else if (isSalinityIdeal || isPHNeutral) {
            return "Aceitável";
        } else {
            return "Ruim";
        }
    }
}
