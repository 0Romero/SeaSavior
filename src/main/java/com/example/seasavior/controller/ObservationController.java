package com.example.seasavior.controller;



import com.example.seasavior.dto.ObservationCreateDTO;
import com.example.seasavior.dto.ObservationDTO;
import com.example.seasavior.model.Observation;
import com.example.seasavior.service.ObservationService;
import com.example.seasavior.exception.ObservationNotFoundException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @GetMapping
    public Page<ObservationDTO> getAllObservations(Pageable pageable) {
        return observationService.findAll(pageable).map(this::convertToDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ObservationDTO>> getObservationById(@PathVariable Long id) {
        Observation observation = observationService.findById(id)
                .orElseThrow(() -> new ObservationNotFoundException(id));
        ObservationDTO observationDTO = convertToDTO(observation);
        EntityModel<ObservationDTO> resource = EntityModel.of(observationDTO);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllObservations(null));
        resource.add(linkTo.withRel("all-observations"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<ObservationDTO> createObservation(@Valid @RequestBody ObservationCreateDTO observationCreateDTO) {
        Observation observation = convertToEntity(observationCreateDTO);
        Observation savedObservation = observationService.save(observation);
        return ResponseEntity.ok(convertToDTO(savedObservation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObservationDTO> updateObservation(@PathVariable Long id, @Valid @RequestBody ObservationCreateDTO observationCreateDTO) {
        if (!observationService.findById(id).isPresent()) {
            throw new ObservationNotFoundException(id);
        }
        Observation observation = convertToEntity(observationCreateDTO);
        observation.setId(id);
        Observation updatedObservation = observationService.save(observation);
        return ResponseEntity.ok(convertToDTO(updatedObservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObservation(@PathVariable Long id) {
        if (!observationService.findById(id).isPresent()) {
            throw new ObservationNotFoundException(id);
        }
        observationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ObservationDTO convertToDTO(Observation observation) {
        ObservationDTO observationDTO = new ObservationDTO();
        observationDTO.setId(observation.getId());
        observationDTO.setLocation(observation.getLocation());
        observationDTO.setPh(observation.getPh());
        observationDTO.setSalinity(observation.getSalinity());
        observationDTO.setTemperature(observation.getTemperature());
        observationDTO.setSpeciesPresent(observation.getSpeciesPresent());
        return observationDTO;
    }

    private Observation convertToEntity(ObservationCreateDTO observationCreateDTO) {
        Observation observation = new Observation();
        observation.setLocation(observationCreateDTO.getLocation());
        observation.setPh(observationCreateDTO.getPh());
        observation.setSalinity(observationCreateDTO.getSalinity());
        observation.setTemperature(observationCreateDTO.getTemperature());
        observation.setSpeciesPresent(observationCreateDTO.getSpeciesPresent());
        return observation;
    }
}