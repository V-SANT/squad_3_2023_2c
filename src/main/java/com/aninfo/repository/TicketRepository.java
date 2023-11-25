package com.aninfo.repository;

import com.aninfo.model.Ticket;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Optional<Ticket> findTicketByName(String name);

    @Override
    Optional<Ticket> findById(Long id);

    @Override
    List<Ticket> findAll();

}