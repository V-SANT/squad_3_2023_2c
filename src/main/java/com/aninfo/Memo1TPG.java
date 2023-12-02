package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.TicketService;
import com.aninfo.exceptions.CouldNotAccessAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
// @EnableJpaRepositories
@EnableSwagger2

public class Memo1TPG {
	
	@Autowired
	private TicketService ticketService;


	public static void main(String[] args) {
		SpringApplication.run(Memo1TPG.class, args);
	}
	
	@PostMapping("/tickets")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket createTicket(@RequestParam String title, @RequestParam String info, @RequestParam Severity severity,@RequestParam Status status ,@RequestParam Priority priority, @RequestParam String product, @RequestParam String version, @RequestParam Long employeeId, @RequestBody List<Long> associatedTasksIds, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate estimatedFinishDate)
	{
		return ticketService.createTicket(title, info, status, severity, priority, product, version, employeeId, associatedTasksIds, startDate, estimatedFinishDate);
	}
	// public Ticket createTicket(@RequestBody Ticket ticket)
	// {
	// 	return ticketService.createTicket(ticket);
	// }

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.findAll();
	}

	@GetMapping("/tickets/{code}")
	public Ticket getTicket(@PathVariable Long code) {
		return ticketService.findByCode(code);
	}

	@DeleteMapping("/tickets/{code}")
	public void deleteTicket(@PathVariable Long code) {
		ticketService.deleteByCode(code);
	}

	@PutMapping("/tickets/{code}")
	public Ticket updateTicket(@PathVariable Long code, @RequestParam String title, @RequestParam String description, @RequestParam Status status, @RequestParam Severity severity, @RequestParam Priority priority, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate estimatedClosingDate){
		return ticketService.updateTicket(code, title, description, status, severity, priority, estimatedClosingDate);
	}

	// @GetMapping("/employees")
	// public Collection<Employee> getEmployees() {
	// 	RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
	// 	ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(
	// 			"https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos",
	// 			Employee[].class);

	// 	if (responseEntity.getStatusCode() == HttpStatus.OK) {
	// 		Employee[] employees = responseEntity.getBody();

	// 		if (employees != null) {
	// 			return Arrays.asList(employees);
	// 		}
	// 	}

	// 	return Collections.emptyList();
	// }
	// @GetMapping("/employees/{employeeId}")
	// public  ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId)
	// {
	// 	Optional<Employee> employeeOptional = getEmployees().stream().filter(employee -> employee.getIdNumber().equals(employeeId)).findFirst();
	// 	return ResponseEntity.of(employeeOptional);

	// }

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
