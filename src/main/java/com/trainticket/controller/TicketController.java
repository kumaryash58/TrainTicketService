package com.trainticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trainticket.dto.TicketRequest;
import com.trainticket.model.Receipt;
import com.trainticket.model.SeatAssignment;
import com.trainticket.service.TicketService;

import java.util.*;

@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("/purchase")
	public ResponseEntity<Receipt> purchaseTicket(@RequestBody TicketRequest ticketRequest) {
		return ResponseEntity.ok(ticketService.purchaseTicket(ticketRequest));
	}

	@GetMapping("/receipt/{email}")
	public ResponseEntity<Receipt> getReceipt(@PathVariable String email) {
		return ResponseEntity.ok(ticketService.getReceipt(email));
	}

	@GetMapping("/seats")
	public ResponseEntity<List<SeatAssignment>> getSeatsBySection(@RequestParam String section) {
		return ResponseEntity.ok(ticketService.getSeatsBySection(section));
	}

	@DeleteMapping("/remove/{email}")
	public ResponseEntity<String> removeUser(@PathVariable String email) {
		ticketService.removeUser(email);
		return ResponseEntity.ok("User removed successfully.");
	}

	@PutMapping("/modify-seat")
	public ResponseEntity<String> modifySeat(@RequestParam String email, @RequestParam String newSection) {
		ticketService.modifySeat(email, newSection);
		return ResponseEntity.ok("Seat updated successfully.");
	}
}