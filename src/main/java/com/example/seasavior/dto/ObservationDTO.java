package com.example.seasavior.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;

import jakarta.persistence.Transient;
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
    
    
     public void addSpecies(String species) {
        this.speciesPresent.add(species);
    }
    
    
    @Transient // Para evitar persistência no banco de dados
    private List<Link> customLinks = new ArrayList<>();

    // Método para adicionar um link personalizado
    public void add(Link link) {
        customLinks.add(link);
    }

    // Método personalizado para obter os links personalizados
    public List<Link> getCustomLinks() {
        return customLinks;
    }

    // Getters e Setters para os atributos existentes

   
    
}