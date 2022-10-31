package com.argentina_programa.backend_portafolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.argentina_programa.backend_portafolio.models.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {
    
}
