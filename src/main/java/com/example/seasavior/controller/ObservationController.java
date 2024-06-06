package com.example.seasavior.controller;

import com.example.seasavior.dto.ObservationDTO;
import com.example.seasavior.model.Cliente;
import com.example.seasavior.model.Observation;
import com.example.seasavior.service.ClienteService;
import com.example.seasavior.service.ObservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("observations")
@Tag(name = "observations")
public class ObservationController {

    @Autowired
    private ObservationService observationService;
    
    @Autowired
    private ClienteService clienteService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return result.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }

    @Operation(summary = "Criar uma nova observação associada a um cliente")
    @PostMapping("/{clienteId}")
    public ResponseEntity<ObservationDTO> criarObservacao(@PathVariable Long clienteId, @Valid @RequestBody ObservationDTO observationDTO) {
        // Verificar se o cliente existe
        Cliente cliente = clienteService.buscarClientePorId(clienteId);
        if (cliente == null) {

            return ResponseEntity.notFound().build();
        }

        // Criar a observação
        Observation observation = new Observation(
                observationDTO.getLocation(),
                observationDTO.getPh(),
                observationDTO.getSalinity(),
                observationDTO.getTemperature(),
                observationDTO.getSpeciesPresent()
        );

        observation.setWaterQuality(observationService.calculateWaterQuality(
                observationDTO.getSalinity(),
                observationDTO.getPh(),
                observationDTO.getTemperature()
        ));

        // Associar a observação ao cliente
        observation.setCliente(cliente);

        Observation novaObservation = observationService.save(observation);
        ObservationDTO novaObservationDTO = convertToDTO(novaObservation);
        addLinks(novaObservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaObservationDTO);
    }

    @Operation(summary = "Listar todas as observações")
    @GetMapping
    public ResponseEntity<List<ObservationDTO>> listarObservacoes() {
        List<Observation> observations = observationService.findAll();
        List<ObservationDTO> observationDTOs = observations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        for (ObservationDTO observationDTO : observationDTOs) {
            addLinks(observationDTO);
        }

        return ResponseEntity.ok(observationDTOs);
    }

    @Operation(summary = "Buscar observação por ID")
    @GetMapping("{id}")
    public ResponseEntity<ObservationDTO> buscarObservacaoPorId(@PathVariable Long id) {
        Observation observation = observationService.findById(id).orElse(null);
        if (observation != null) {
            ObservationDTO observationDTO = convertToDTO(observation);
            addLinks(observationDTO);
            return ResponseEntity.ok(observationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar observação")
    @PutMapping("{id}")
    public ResponseEntity<ObservationDTO> atualizarObservacao(@PathVariable Long id, @Valid @RequestBody ObservationDTO observationDTO) {
        Observation observation = new Observation(
                observationDTO.getLocation(),
                observationDTO.getPh(),
                observationDTO.getSalinity(),
                observationDTO.getTemperature(),
                observationDTO.getSpeciesPresent()
        );
        observation.setId(id);

        observation.setWaterQuality(observationService.calculateWaterQuality(
                observationDTO.getSalinity(),
                observationDTO.getPh(),
                observationDTO.getTemperature()
        ));

        Observation updatedObservation = observationService.update(observation);
        ObservationDTO updatedObservationDTO = convertToDTO(updatedObservation);
        addLinks(updatedObservationDTO);
        return ResponseEntity.ok(updatedObservationDTO);
    }

    @Operation(summary = "Deletar observação")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarObservacao(@PathVariable Long id) {
        observationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ObservationDTO convertToDTO(Observation observation) {
        ObservationDTO dto = new ObservationDTO();
        dto.setId(observation.getId());
        dto.setLocation(observation.getLocation());
        dto.setPh(observation.getPh());
        dto.setSalinity(observation.getSalinity());
        dto.setTemperature(observation.getTemperature());
        dto.setSpeciesPresent(observation.getSpeciesPresent());
        dto.setWaterQuality(observation.getWaterQuality());
        return dto;
    }

    private void addLinks(ObservationDTO observationDTO) {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ObservationController.class).buscarObservacaoPorId(observationDTO.getId())).withSelfRel();
        observationDTO.add(selfLink);
    }
}
