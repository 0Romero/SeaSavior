package com.example.seasavior.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.seasavior.repository.ObservationRepository;
import com.example.seasavior.model.Observation;


@Service
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    public Page<Observation> findAll(Pageable pageable) {
        return observationRepository.findAll(pageable);
    }

    public Optional<Observation> findById(Long id) {
        return observationRepository.findById(id);
    }

    public Observation save(Observation observation) {
        return observationRepository.save(observation);
    }

    public void deleteById(Long id) {
        observationRepository.deleteById(id);
    }
}
