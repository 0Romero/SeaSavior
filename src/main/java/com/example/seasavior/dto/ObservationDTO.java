package com.example.seasavior.dto;

import java.util.List;

import lombok.Data;

@Data
public class ObservationDTO {
    private Long id;
    private String location;
    private double ph;
    private double salinity;
    private double temperature;
    private List<String> speciesPresent;
    private String waterQuality;
     // Adicionando o atributo para armazenar a qualidade da água

    // Getters e Setters para os atributos existentes

   
    
}