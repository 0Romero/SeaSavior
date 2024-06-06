package com.example.seasavior.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Localização Obrigatoria....")
    @Size(min= 3 , max=255)
    private String location;

    @NonNull
    private Double ph;

    @NonNull
    private Double salinity;

    @NonNull
    private Double temperature;

    @NotEmpty(message = "A lista de espécies não pode estar vazia")
    @Size(min = 1, message = "A lista de espécies deve conter pelo menos uma espécie")
    private List<String> speciesPresent;
    

    private String waterQuality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

    public Observation(String location, Double ph, Double salinity, Double temperature, List<String> speciesPresent) {
        this.location = location;
        this.ph = ph;
        this.salinity = salinity;
        this.temperature = temperature;
        this.speciesPresent = speciesPresent;
    }

    public Observation() {
        // Default constructor
    }
}
