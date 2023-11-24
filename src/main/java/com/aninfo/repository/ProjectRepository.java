package com.aninfo.repository;

import com.aninfo.model.Project;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Optional<Project> findProjectByName(String name);

    @Override
    List<Project> findAll();

}
