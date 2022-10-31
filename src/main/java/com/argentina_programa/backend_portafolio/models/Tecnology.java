package com.argentina_programa.backend_portafolio.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tecnologies")
public class Tecnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tecnology_id")
    Long id;

    @Column(name = "name", length = 20, nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "project_id", nullable = false)
    Project project;

    public Tecnology(Long id, String name, Project project) {
        this.id = id;
        this.name = name;
        this.project = project;
    }

    public Tecnology() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
