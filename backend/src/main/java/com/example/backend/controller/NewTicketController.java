package com.example.backend.controller;

import com.example.backend.model.NewTicket;
import com.example.backend.repository.NewTicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/newticket")
//@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class NewTicketController {  // ✅ Renamed controller

    @Autowired
    private NewTicketRepository NewTicketRepository;

    // Create Ticket
    @PostMapping("/create")
    public NewTicket createTicket(@RequestBody NewTicket ticket) {
        return NewTicketRepository.save(ticket);  // ✅ Corrected method call (not static)
    }

    // Get All Tickets
    @GetMapping
    public List<NewTicket> getAllTickets() {
        return NewTicketRepository.findAll();  // ✅ Corrected method call (not static)
    }
}


