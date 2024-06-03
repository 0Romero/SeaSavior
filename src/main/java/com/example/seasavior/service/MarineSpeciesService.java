package com.example.seasavior.service;

import com.example.seasavior.model.MarineSpecies;
import com.example.seasavior.repository.MarineSpeciesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarineSpeciesService {

    @Autowired
    private MarineSpeciesRepository speciesRepository;

    public MarineSpecies saveOrUpdateSpecies(MarineSpecies species) {
        // Implemente aqui a lógica para verificar se a espécie está em risco de extinção
        boolean isEndangered = checkEndangered(species);
        species.setEndagered(isEndangered);

        return speciesRepository.save(species);
    }

    private boolean checkEndangered(MarineSpecies species) {
        // Exemplo de critérios para determinar se uma espécie está em risco de extinção
        // Neste exemplo, consideramos uma espécie em risco se a descrição contiver a palavra "ameaçada"
        String description = species.getDescription().toLowerCase();
        return description.contains("ameaçada");
    }

    public MarineSpecies getSpeciesById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpeciesById'");
    }

    public List<MarineSpecies> getAllSpecies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSpecies'");
    }

    public void deleteSpecies(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSpecies'");
    }
}
