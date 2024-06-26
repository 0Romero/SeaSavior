package com.example.seasavior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.seasavior.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
   Cliente findByCpf(String cpf);
}