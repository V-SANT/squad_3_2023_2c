package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.TicketService;
import com.aninfo.exceptions.CouldNotAccessAPI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.h2.util.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.ArrayList;
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


@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
@Configuration
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

	@Transactional
	@PutMapping("/tickets/{code}/associatedTask")
	public Long createTicketAssociatedTask(@PathVariable Long code, @RequestParam Long projectId, @RequestBody TaskCreationRequest task){
		ticketService.findByCode(code);
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
		String url = "https://psa-proyecto.onrender.com/projects/" + projectId + "/tasks";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<TaskCreationRequest> requestEntity = new HttpEntity<>(task, headers);
		ResponseEntity<TaskResponse> responseEntity = restTemplate.postForEntity(
				url,
				requestEntity,
				TaskResponse.class);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			TaskResponse gottenTask = responseEntity.getBody();

			if (gottenTask != null) {
				List<Long> taskId = new ArrayList<Long>();
				taskId.add(gottenTask.getId());
				ticketService.updateTicket(code, null, null, null, null, null, null, null, null, null, Optional.of(taskId), null);
				return gottenTask.getId();
			}
		}
		return null;
	}

	@DeleteMapping("/tickets/{code}")
	public void deleteTicket(@PathVariable Long code) {
		ticketService.deleteByCode(code);
	}

	@PutMapping("/tickets/{code}")
	public Ticket updateTicket(
			@PathVariable Long code,
			@RequestParam(required = false) Optional<String> title,
			@RequestParam(required = false) Optional<String> description,
			@RequestParam(required = false) Optional<Status> status,
			@RequestParam(required = false) Optional<Severity> severity,
			@RequestParam(required = false) Optional<Priority> priority,
			@RequestParam(required = false) Optional<String> product,
			@RequestParam(required = false) Optional<String> version,
			@RequestParam(required = false) Optional<Long> employeeId,
			@RequestParam(required = false) Optional<Long> clientId,
			@RequestBody Optional<List<Long>> taskIds,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> estimatedClosingDate
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