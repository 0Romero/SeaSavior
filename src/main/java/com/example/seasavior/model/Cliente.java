package com.example.seasavior.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank(message= "Nome Obrigatório")
    @Size(min= 3 , max=255)
    private String name; 

    @NotBlank(message= "Email Obirgatório")
    @Email
    private String email;

    

}
