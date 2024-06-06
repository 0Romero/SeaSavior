package com.example.seasavior.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    
    @NotBlank(message = "Cpf Obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    private String cpf;

    @NotBlank(message= "Email Obirgatório")
    @Email
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Observation> observations;

     // Lista de links do HATEOAS
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

    public Cliente() {
    }


    public Cliente( String name,String cpf, String email) {
        
        this.name = name;
        this.cpf = cpf;
        this.email = email;

    }

    
    
}
