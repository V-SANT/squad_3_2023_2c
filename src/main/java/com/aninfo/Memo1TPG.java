package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.TicketService;
import com.aninfo.exceptions.CouldNotAccessAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
public class Memo1TPG {

	@Autowired
	private TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(Memo1TPG.class, args);
	}

	@PostMapping("/tickets")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket createTicket(
		@RequestBody TicketCreationRequest ticketCreationRequest
	){
		return ticketService.createTicket(
			ticketCreationRequest.getTitle(),
			ticketCreationRequest.getDescription(),
			ticketCreationRequest.getMappedStatus(),
			ticketCreationRequest.getMappedSeverity(),
			ticketCreationRequest.getMappedPriority(),
			ticketCreationRequest.getProduct(),
			ticketCreationRequest.getVersion(),
			Long.parseLong(ticketCreationRequest.getClientId()),
			Long.parseLong(ticketCreationRequest.getEmployeeId()),
			LocalDate.parse(ticketCreationRequest.getEstimatedClosingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
		);
	}

	        // String description = ticketRequest.getDescription();
        // Status status = ticketRequest.getMappedStatus();
        // Severity severity = ticketRequest.getMappedSeverity();
        // Priority priority = ticketRequest.getMappedPriority();
        // String product = ticketRequest.getProduct();
        // String version = ticketRequest.getVersion();
        // Long clientId = Long.parseLong(ticketRequest.getClientId());
        // Long employeeId = Long.parseLong(ticketRequest.getEmployeeId());
        // LocalDate estimatedClosingDate = LocalDate.parse(ticketRequest.getEstimatedClosingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

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
	public Ticket updateTicket(
			@PathVariable Long code,
			@RequestParam @Nullable String title,
			@RequestParam @Nullable String description,
			@RequestParam @Nullable Status status,
			@RequestParam @Nullable Severity severity,
			@RequestParam @Nullable Priority priority,
			@RequestParam @Nullable String product,
			@RequestParam @Nullable String version,
			@RequestParam @Nullable Long employeeId,
			@RequestParam @Nullable Long clientId,
			@RequestBody @Nullable List<Long> taskIds,
			@RequestParam @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate estimatedClosingDate
	){
		return ticketService.updateTicket(code, title, description, status, severity, priority, product, version, clientId, employeeId, taskIds, estimatedClosingDate);
	}

	@GetMapping("/clients")
	public Collection<Client> getClients() {
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		ResponseEntity<Client[]> responseEntity = restTemplate.getForEntity(
				"https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes",
				Client[].class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Client[] clients = responseEntity.getBody();

			if (clients != null) {
				return Arrays.asList(clients);
			}
		}

		return Collections.emptyList();
	}

	@GetMapping("/clients/{clientId}")
	public  ResponseEntity<Client> getClient(@PathVariable Long clientId)
	{
		Optional<Client> clientOptional = getClients().stream().filter(client -> client.getId().equals(clientId)).findFirst();
		return ResponseEntity.of(clientOptional);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}