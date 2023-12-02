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

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.findAll();
	}

	@GetMapping("/tickets/{code}")
	public Ticket getTicket(@PathVariable Long code) {
		return ticketService.findByCode(code);
	}

	@GetMapping("/tickets/associatedTask")
	public Collection<Long> getTicketsAssociatedTask(@RequestParam Long taskId){
		return ticketService.getTicketsAssociatedTask(taskId);
	}

	@DeleteMapping("/tickets/{code}")
	public void deleteTicket(@PathVariable Long code) {
		ticketService.deleteByCode(code);
	}

	@PutMapping("/tickets/{code}")
	public Ticket updateTicket(@PathVariable Long code, @RequestParam String title, @RequestParam String description, @RequestParam Status status, @RequestParam Severity severity, @RequestParam Priority priority, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate estimatedClosingDate){
		return ticketService.updateTicket(code, title, description, status, severity, priority, estimatedClosingDate);
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
