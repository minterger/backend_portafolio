package com.argentina_programa.backend_portafolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.argentina_programa.backend_portafolio.models.Skill;

import java.util.List;;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    public List<Skill> findByType(String type);
}
