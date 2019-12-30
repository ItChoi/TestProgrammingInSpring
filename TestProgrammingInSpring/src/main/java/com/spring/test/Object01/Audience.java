package com.spring.test.Object01;

//관람객 표현 클래스, 관람객은 소지품을 보관하기 위해 가방을 소지할 수 있다.
public class Audience {
	
	private Bag bag;
	
	public Audience(Bag bag) {
		this.bag = bag;
	}
	
	/* 2단계
	 * public Bag getBag() { return bag; }
	 */
	
	/** 2단계로 추가했지만, 3단계를 위해 주석 - bag에 추가
	public Long buy(Ticket ticket) {
		if (bag.hasInvitation()) {
			bag.setTicket(ticket);
			return 0L;
		} else {
			bag.setTicket(ticket);
			bag.minusAmount(ticket.getFee());
			return ticket.getFee();
		}
	}
	*/
	public Long buy(Ticket ticket) {
		return bag.hold(ticket);
	}
} 