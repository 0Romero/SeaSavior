package com.example.seasavior.dto;

import lombok.Data;

@Data
public class ObservationDTO {
    private Long id;
    private String location;
    private Double ph;
    private Double salinity;
    private Double temperature;
    private String speciesPresent;
}