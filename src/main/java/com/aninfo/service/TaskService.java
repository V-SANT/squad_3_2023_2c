package com.aninfo.service;

import com.aninfo.model.Priority;
import com.aninfo.model.Task;
import com.aninfo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Long projectId, String name, String description, Priority priority, Long estimatedDuration)
    {
        Task task = new Task(projectId, name, description, priority, estimatedDuration);
        return taskRepository.save(task);
    }
    public void save(Task task)
    {
        taskRepository.save(task);
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Collection<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Collection<Task> getTasksByProject(Long id) {
        return taskRepository.findTasksByProjectId(id);
    }
}
