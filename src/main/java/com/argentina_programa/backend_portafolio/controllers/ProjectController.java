package com.argentina_programa.backend_portafolio.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argentina_programa.backend_portafolio.Exceptions.ResourceNotFoundException;
import com.argentina_programa.backend_portafolio.models.Project;
import com.argentina_programa.backend_portafolio.repositories.ProjectRepositorio;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepositorio repositorio;

    @GetMapping
    public List<Project> listProjects() {
        return repositorio.findAll();
    }

    @PostMapping
    public Project newProject(@RequestBody Project project) {
        return repositorio.save(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@RequestBody Project projectBody, @PathVariable Long id) {
        Project project = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Proyecto no fue encontrado"));
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> editProject(@RequestBody Project projectBody, @PathVariable Long id) {
        Project project = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Proyecto no fue encontrado"));

        project.setName(projectBody.getName());
        project.setDescription(projectBody.getDescription());
        project.setImgUrl(projectBody.getImgUrl());
        project.setLinkGithub(projectBody.getLinkGithub());
        project.setLinkProject(projectBody.getLinkProject());
        project.setTecnologies(projectBody.getTecnologies());

        Project responseProject = repositorio.save(project);

        return ResponseEntity.ok(responseProject);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProject(@PathVariable Long id) {
        Project project = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Proyecto no fue encontrado"));

        repositorio.delete(project);

        Map<String, Boolean> response = new HashMap<>();

        response.put("Eliminar", true);
        return ResponseEntity.ok(response);

    }
}
