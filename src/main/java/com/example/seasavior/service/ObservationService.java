package com.example.seasavior.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.seasavior.repository.ObservationRepository;
import com.example.seasavior.exception.ObservationNotFoundException;
import com.example.seasavior.model.Observation;




@Service
public class ObservationService {

    @Autowired
    private ObservationRepository observationRepository;

    public Observation save(Observation observation) {
        return observationRepository.save(observation);
    }

    public Page<Observation> findAll(Pageable pageable) {
        return observationRepository.findAll(pageable);
    }

    public Optional<Observation> findById(Long id) {
        return observationRepository.findById(id);
    }

    public void deleteById(Long id) {
        observationRepository.deleteById(id);
    }
    
    public Observation update(Observation observation) {
        // Verifica se a observação existe no banco de dados
        if (observationRepository.existsById(observation.getId())) {
            return observationRepository.save(observation);
        } else {
            throw new ObservationNotFoundException(observation.getId());
        }
    }
    
    public String calculateWaterQuality(double salinity, double pH, double temperature) {
        // Aqui você pode implementar a lógica para calcular a qualidade da água
        // com base nos valores de salinidade, pH e temperatura fornecidos
        
        // Critérios para avaliar a qualidade da água
        boolean isSalinityIdeal = salinity >= 15 && salinity <= 30; // Salinidade ideal entre 15 e 30 ppt (partes por mil)
        boolean isPHNeutral = pH >= 6.5 && pH <= 8.5; // pH neutro entre 6.5 e 8.5
        boolean isTemperatureOptimal = temperature >= 20 && temperature <= 25; // Temperatura ideal entre 20°C e 25°C
        
        // Verificar os critérios para determinar a qualidade da água
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
