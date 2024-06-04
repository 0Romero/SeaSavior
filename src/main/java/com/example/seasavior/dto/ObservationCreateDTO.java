package com.example.seasavior.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ObservationCreateDTO {
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
    private String speciesPresent;
}