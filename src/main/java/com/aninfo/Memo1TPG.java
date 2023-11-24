package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.ProjectService;
import com.aninfo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2

public class Memo1TPG {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private TaskService taskService;


	public static void main(String[] args) {
		SpringApplication.run(Memo1TPG.class, args);
	}

	@PostMapping("/projects")
	public Project createProject(@RequestParam String name, @RequestParam String description, @RequestParam LocalDate startDate, @RequestParam LocalDate estimatedFinishDate)
	{
		return projectService.createProject(name, description, startDate, estimatedFinishDate);
	}

	@PostMapping("/projects/{id}/tasks")
	public Task createTask(@PathVariable Long id, @RequestParam String name, @RequestParam String description, @RequestParam Priority priority, @RequestParam Long estimatedDuration)
	{
		return taskService.createTask(id, name, description, priority, estimatedDuration);
	}

	@GetMapping("/projects")
	public Collection<Project> getProjects() {
		return projectService.findAll();
	}

	@GetMapping("/projects/{id}")
	public Project getProject(@PathVariable Long id) {
		return projectService.findById(id);
	}

	@DeleteMapping("/project/{id}")
	public void deleteProject(@PathVariable Long id) {
		projectService.deleteById(id);
	}


	@GetMapping("/projects/{id}/tasks")
	public Collection<Task> getTasksForProject(@PathVariable Long id) {
		return taskService.getTasksByProject(id);
	}

	@DeleteMapping("/projects/{id}/tasks/{taskId}")
	public void deleteTask(@PathVariable Long taskId) {
		taskService.deleteById(taskId);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
