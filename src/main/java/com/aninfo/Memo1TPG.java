package com.aninfo;

import com.aninfo.model.*;
import com.aninfo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.format.annotation.DateTimeFormat;
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
@EnableJpaRepositories
@EnableSwagger2

public class Memo1TPG {
	
	@Autowired
	private TicketService ticketService;


	public static void main(String[] args) {
		SpringApplication.run(Memo1TPG.class, args);
	}


	@PostMapping("/tickets")
	public Ticket createTicket(@RequestBody String name, @RequestBody String info, @RequestBody Severity severity,@RequestBody Status status ,@RequestBody Priority priority, @RequestBody String creator, @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate estimatedFinishDate)
	{
		return ticketService.createTicket(name, info, status, severity, priority, creator, startDate, estimatedFinishDate);
	}

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
	public Ticket updateTicket(@PathVariable Long code, @RequestParam String name, @RequestParam String info, @RequestParam Status status, @RequestParam Severity severity, @RequestParam Priority priority, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate estimatedFinishDate){
		return ticketService.updateTicket(code, name, info, status, severity, priority, estimatedFinishDate);
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
