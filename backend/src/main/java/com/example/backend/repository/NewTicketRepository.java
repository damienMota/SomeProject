package com.example.backend.repository;

import com.example.backend.model.NewTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewTicketRepository extends JpaRepository<NewTicket, Long> {
}