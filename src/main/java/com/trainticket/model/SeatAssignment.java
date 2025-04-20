package com.trainticket.model;

public class SeatAssignment {
	private String email;
	private String seatSection;

	public SeatAssignment(String email, String seatSection) {
		this.email=email;
		this.seatSection=seatSection;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSeatSection() {
		return seatSection;
	}

	public void setSeatSection(String seatSection) {
		this.seatSection = seatSection;
	}

}