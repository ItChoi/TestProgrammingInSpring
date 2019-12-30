package com.spring.test.Object01;

//판매원, 판매원은 자신이 일하는 매표소를 알고 있어야 한다.
public class TicketSeller {
	// 외부에서 ticketOffice에 직접 접근할 수 없다.
	private TicketOffice ticketOffice;
	
	public TicketSeller(TicketOffice ticketOffice) {
		this.ticketOffice = ticketOffice;
	}
	
	/** 1단계
	public TicketOffice getTicketOffice() {
		return ticketOffice;
	}
	*/
	
	public void sellTo(Audience audience) {
		/** 2단계
		if (audience.getBag().hasInvitation()) {
			Ticket ticket = ticketOffice.getTicket();
			audience.getBag().setTicket(ticket);
		} else {
			Ticket ticket = ticketOffice.getTicket();
			audience.getBag().minusAmount(ticket.getFee());
			ticketOffice.plusAmount(ticket.getFee());
			audience.getBag().setTicket(ticket);
		}
		 */
		// 3단계
		// Ticket ticket = ticketOffice.getTicket();
		// 3단계
		// ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
		ticketOffice.sellTicketTo(audience);
		
	}
}
