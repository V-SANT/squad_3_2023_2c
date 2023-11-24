package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.EmployeeService;
import com.aninfo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class MemoTPG {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(MemoTPG.class, args);
	}

	@PostMapping("/projects")
	public Project createProject(@RequestParam String product, @RequestParam String version, @RequestParam String clientId)
	{
		return projectService.createProject(product, version, clientId);
	}

	@PostMapping("/projects/{projectId}/tasks")
	public Task createTask(@PathVariable String projectId, @RequestParam String description, @RequestParam Long storyPoints, @RequestParam Priority priority, @RequestParam Long estimatedDuration)
	{
		return projectService.createTask(projectId, description, storyPoints, priority, estimatedDuration);
	}

	@PostMapping("/projects/{projectId}/tasks/{taskId}")
	public LogbookEntry createLogbookEntrance(@PathVariable String projectId,@PathVariable String taskId)
	{
		return  projectService.createLogbookEntry(projectId,taskId);
	}

	@PostMapping("/staff")
	public Employee addEmployee(@RequestParam String name, @RequestParam Long dni, @RequestParam Long hourlyPay)
	{
		return employeeService.addEmployee(name, dni, hourlyPay);
	}

	@GetMapping("/projects")
	public Collection<Project> getProjects() {
		return projectService.findAll();
	}

	@GetMapping("/accounts/{cbu}")
	public Project getProject(@PathVariable String id) {
		return projectService.findById(id);
	}

	@PutMapping("/accounts/{cbu}")
	public ResponseEntity<Account> updateProject(@RequestBody Project project) {
		projectService.save(project);
	}

	@DeleteMapping("/project/{id}")
	public void deleteProject(@PathVariable String id) {
		projectService.deleteById(id);
	}


	@GetMapping("/projects/{id}/tasks")
	public Collection<Task> getTaskbyProjec(@PathVariable String id) {
		return projectService.getTasksByProject(id);
	}
	@DeleteMapping("/accounts/{cbu}/transactions/{id}")
	public void deleteTask(@PathVariable Long id) {
		projectService.deleteTask(id);
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
