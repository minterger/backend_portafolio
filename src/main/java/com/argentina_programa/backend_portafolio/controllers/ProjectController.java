package com.argentina_programa.backend_portafolio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argentina_programa.backend_portafolio.models.Project;
import com.argentina_programa.backend_portafolio.repositories.ProjectRepositorio;


@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    private ProjectRepositorio repositorio;

    @GetMapping
    public List<Project> listProjects () {
        return repositorio.findAll();
    }

    @PostMapping
    public Project newProject (@RequestBody Project project) {
        return repositorio.save(project);
    }
}
