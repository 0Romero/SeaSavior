package com.example.seasavior.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.seasavior.model.Cliente;
import com.example.seasavior.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente not found with id: " + id));
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}