package com.example.seasavior.dto;

import java.util.List;

import com.example.seasavior.model.Cliente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ObservationCreateDTO {
    
    private Long clienteId;
    @NotNull
    @Size(min = 2, max = 100)
    private String location;

    @NotNull
    private Double ph;

    @NotNull
    private Double salinity;

    @NotNull
    private Double temperature;

    @NotNull
    @Size(min = 2, max = 255)
    private List<String> speciesPresent;
 
    

    

   

    
}