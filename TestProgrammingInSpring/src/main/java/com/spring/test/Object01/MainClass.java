package com.spring.test.Object01;

public class MainClass {

	public static void main(String[] args) {
		Bag bag1 = new Bag(10000L);
		Audience audience1 = new Audience(bag1);
		
		
		Ticket ticket = new Ticket();
		ticket.setFee(3000L);
		TicketOffice ticketOffice = new TicketOffice(5000L, ticket);
		TicketSeller ticketSeller = new TicketSeller(ticketOffice);
		Theater theater = new Theater(ticketSeller);
		
		theater.enter(audience1);
	}

}
