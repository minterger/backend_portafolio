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
import com.argentina_programa.backend_portafolio.models.Study;
import com.argentina_programa.backend_portafolio.repositories.StudyRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/study")
public class StudyController {
    @Autowired
    private StudyRepository repository;

    @GetMapping
    public List<Study> listStudies() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Study newStudy(@RequestBody Study study) {
        return repository.save(study);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Study> getStudy(@PathVariable Long id) {
        Study study = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Estudio No Fue Encontrado"));
        return ResponseEntity.ok(study);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Study> editStudy(@RequestBody Study studyBody, @PathVariable Long id) {
        Study study = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Estudio No Fue Encontrado"));
        study.setName(studyBody.getName());
        study.setImgUrl(studyBody.getImgUrl());
        study.setModality(studyBody.getModality());
        study.setTitle(studyBody.getTitle());

        Study responseStudy = repository.save(study);
        return ResponseEntity.ok(responseStudy);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStudy(@PathVariable Long id) {
        Study study = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Estudio No Fue Encontrado"));
        repository.delete(study);

        Map<String, Boolean> Response = new HashMap<>();

        Response.put("Eliminar", Boolean.TRUE);
        return ResponseEntity.ok(Response);
    }

}
