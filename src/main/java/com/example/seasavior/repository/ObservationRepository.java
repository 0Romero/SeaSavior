package com.example.seasavior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.seasavior.model.Observation;
@Repository
public interface ObservationRepository extends JpaRepository< Observation,Long> {
    
}
