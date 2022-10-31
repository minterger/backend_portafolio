package com.argentina_programa.backend_portafolio.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    Long id;

    @Column(name = "name", length = 60, nullable = false)
    String name;
    @Column(name = "img_url", length = 200, nullable = false)
    String imgUrl;
    @Column(name = "description", length = 500, nullable = false)
    String description;
    @Column(name = "link_project", length = 200, nullable = false)
    String linkProject;
    @Column(name = "link_github", length = 200, nullable = false)
    String linkGithub;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    List<Tecnology> tecnologies;

    public Project(Long id, String name, String imgUrl, String description, String linkProject, String linkGithub,
            List<Tecnology> tecnologies) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.linkProject = linkProject;
        this.linkGithub = linkGithub;
        this.tecnologies = tecnologies;
    }

    public Project() {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkProject() {
        return linkProject;
    }

    public void setLinkProject(String linkProject) {
        this.linkProject = linkProject;
    }

    public String getLinkGithub() {
        return linkGithub;
    }

    public void setLinkGithub(String linkGithub) {
        this.linkGithub = linkGithub;
    }

    public List<Tecnology> getTecnologies() {
        return tecnologies;
    }

    public void setTecnologies(List<Tecnology> tecnologies) {
        this.tecnologies = tecnologies;
    }
}
