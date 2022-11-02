package com.argentina_programa.backend_portafolio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argentina_programa.backend_portafolio.Exceptions.ResourceNotFoundException;
import com.argentina_programa.backend_portafolio.models.Skill;
import com.argentina_programa.backend_portafolio.repositories.SkillRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    private SkillRepository repository;

    @GetMapping("/framework")
    public List<Skill> listSkillsFramework() {
        return repository.findByType("framework");
    }

    @GetMapping("/lenguaje")
    public List<Skill> listSkillsLenguaje() {
        return repository.findByType("lenguaje");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Skill newSkill(@RequestBody Skill skill) {
        return repository.save(skill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkill(@PathVariable Long id) {
        Skill skill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La Skill No Fue Encontrada"));
        return ResponseEntity.ok(skill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Skill> editSkill(@RequestBody Skill bodySkill, @PathVariable Long id) {
        Skill skill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La Skill No Fue Encontrada"));

        skill.setName(bodySkill.getName());
        skill.setType(bodySkill.getType());
        skill.setImgUrl(bodySkill.getImgUrl());

        Skill responseSkill = repository.save(skill);
        return ResponseEntity.ok(responseSkill);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSkill(@PathVariable Long id) {
        Skill skill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La Skill No Fue Encontrada"));

        repository.delete(skill);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminar", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
