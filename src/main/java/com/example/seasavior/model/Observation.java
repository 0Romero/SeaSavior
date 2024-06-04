package com.example.seasavior.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message= "Espécies Marinhas presentes são Obrigatório caso for nulo apenas descreva como nulo....")
    private String speciesPresent;

    // Getters and Setters

    // Constructor, equals, hashCode, toString
}
