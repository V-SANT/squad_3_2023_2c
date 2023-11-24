package com.aninfo.repository;

import com.aninfo.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findTasksByProjectId(Long projectId);

    @Override
    List<Task> findAll();

    @Override
    List<Task> findAllById(Iterable<Long> ids);

}
