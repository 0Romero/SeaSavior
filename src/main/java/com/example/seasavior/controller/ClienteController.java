package com.example.seasavior.controller;

import com.example.seasavior.dto.ClienteCreateDTO;
import com.example.seasavior.dto.ClienteDTO;
import com.example.seasavior.model.Cliente;
import com.example.seasavior.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> getAllClients() {
        return clienteService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClienteDTO>> getClientById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        ClienteDTO clienteDTO = convertToDTO(cliente);
        EntityModel<ClienteDTO> resource = EntityModel.of(clienteDTO);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllClients());
        resource.add(linkTo.withRel("all-cliente"));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createClient(@Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {
        Cliente cliente = convertToEntity(clienteCreateDTO);
        Cliente savedCliente = clienteService.save(cliente);
        return ResponseEntity.ok(convertToDTO(savedCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {
        Cliente cliente = convertToEntity(clienteCreateDTO);
        cliente.setId(id);
        Cliente updatedCliente = clienteService.update(cliente);
        return ResponseEntity.ok(convertToDTO(updatedCliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setName(cliente.getName());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteCreateDTO clienteCreateDTO) {
        Cliente cliente = new Cliente();
        cliente.setName(clienteCreateDTO.getName());
        cliente.setEmail(clienteCreateDTO.getEmail());
        return cliente;
    }
}