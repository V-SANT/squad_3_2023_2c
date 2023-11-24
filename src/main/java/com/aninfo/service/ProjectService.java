package com.aninfo.service;

import com.aninfo.exceptions.ProjectNameAlreadyTakenException;
import com.aninfo.model.Priority;
import com.aninfo.model.Project;
import com.aninfo.exceptions.InvalidProjectException;
import com.aninfo.model.Task;
import com.aninfo.repository.ProjectRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(String name, String description, LocalDate startDate, LocalDate estimatedFinishDate) {
        projectRepository.findProjectByName(name).ifPresent(x -> {throw new ProjectNameAlreadyTakenException("Name already taken");});
        Project project = new Project(name, description, startDate, estimatedFinishDate);
        return projectRepository.save(project);

    }

    public Collection<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project findByName(String name) {
        return projectRepository.findProjectByName(name).orElseThrow(() -> new InvalidProjectException("No project found with that name"));
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new InvalidProjectException("No project found with that name"));
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }

}
