package com.example.seasavior.controller;

import com.example.seasavior.model.MarineSpecies;
import com.example.seasavior.service.MarineSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class MarineSpeciesController {

    @Autowired
    private MarineSpeciesService speciesService;

    @PostMapping
    public ResponseEntity<MarineSpecies> createSpecies(@RequestBody MarineSpecies species) {
        MarineSpecies savedSpecies = speciesService.saveOrUpdateSpecies(species);
        return new ResponseEntity<>(savedSpecies, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarineSpecies> getSpeciesById(@PathVariable Long id) {
        MarineSpecies species = speciesService.getSpeciesById(id);
        if (species != null) {
            return new ResponseEntity<>(species, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MarineSpecies>> getAllSpecies() {
        List<MarineSpecies> speciesList = speciesService.getAllSpecies();
        return new ResponseEntity<>(speciesList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarineSpecies> updateSpecies(@PathVariable Long id, @RequestBody MarineSpecies updatedSpecies) {
        MarineSpecies species = speciesService.getSpeciesById(id);
        if (species != null) {
            updatedSpecies.setId(id);
            MarineSpecies savedSpecies = speciesService.saveOrUpdateSpecies(updatedSpecies);
            return new ResponseEntity<>(savedSpecies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable Long id) {
        speciesService.deleteSpecies(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}