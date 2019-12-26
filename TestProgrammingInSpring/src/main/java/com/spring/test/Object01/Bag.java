package com.spring.test.Object01;

//관람객의 소지품 Bag
public class Bag {

	// 관람객 소지품 현금, 초대장, 티켓
	private Long amount;
	private Invitation invitation;
	private Ticket ticket;
	
	// Bag은 현금과 초대장을 가진 당첨자, 초대장이 없는 관람객 두 가지 상태
		// Bag 인스턴스 생성 시점에 제약 강제하도록 생성자 추가
		public Bag(long amount) {
			this(null, amount);
		}
		
		public Bag(Invitation invitation, long amount) {
			this.invitation = invitation;
			this.amount = amount;
		}
	
	// 초대장 여부 판단
	public boolean hasInvitation() {
		return invitation != null;
	}
	
	// 티켓 여부 판단
	public boolean hasTicket() {
		return ticket != null;
	}
	
	// 초대장을 티켓으로 교환
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	// 현금 증가
	public void minusAmount(Long amount) {
		this.amount -= amount;
	}
	
	// 현금 감소
	public void plusAmount(Long amount) {
		this.amount += amount;
	}
	
}