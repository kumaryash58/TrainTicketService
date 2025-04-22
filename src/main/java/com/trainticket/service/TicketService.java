package com.trainticket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.trainticket.dto.TicketRequest;
import com.trainticket.model.Receipt;
import com.trainticket.model.SeatAssignment;

@Service
public class TicketService {
	private final Map<String, Receipt> ticketMap = new ConcurrentHashMap<>();

	public Receipt purchaseTicket(TicketRequest ticketRequest) {
		String seatSection = assignSection();
		Receipt receipt = new Receipt();
		receipt.setUser(ticketRequest.getUser());
		receipt.setSeatSection(seatSection);
		receipt.setFrom(ticketRequest.getFrom());
		receipt.setTo(ticketRequest.getTo());
		receipt.setPricePaid(ticketRequest.getPricePaid());
		ticketMap.put(ticketRequest.getUser().getEmail(), receipt);
		return receipt;
	}

	public Receipt getReceipt(String email) {
		return ticketMap.get(email);
	}

	public List<SeatAssignment> getSeatsBySection(String section) {
		List<SeatAssignment> result = new ArrayList<>();
		for (Receipt r : ticketMap.values()) {
			if (r.getSeatSection().equalsIgnoreCase(section)) {
				result.add(new SeatAssignment(r.getUser().getEmail(), r.getSeatSection()));
			}
		}
		return result;
	}

	public void removeUser(String email) {
		ticketMap.remove(email);
	}

	public void modifySeat(String email, String newSection) {
		Receipt receipt = ticketMap.get(email);
		if (receipt != null) {
			receipt.setSeatSection(newSection);
		}
	}

	private String assignSection() {
		return Math.random() > 0.5 ? "A" : "B";
	}
}