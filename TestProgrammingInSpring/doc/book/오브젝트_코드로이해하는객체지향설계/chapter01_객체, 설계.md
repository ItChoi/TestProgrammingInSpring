### 객체, 설계
- 이론이 먼저일까? 실무가 먼저일까?
    - 처음 배울 시기에는 흐름이 잡혀있지 않기 때문에 이론을 적립할 수 없는 초기에는 실무가 급속한 발전을 이룬다고 한다. 그 다음에 실무의 실용성을 입증할 수 있는 이론을 적립하는 것이 좋다. 즉, 초반에는 이론이 실무보다 급속한 성장을 이루지만, 실무가 익숙해지고 어느 정도 가닥이 잡히고 나면, 그 후에는 이론이 실무를 추월한다고 본다.
    <br/>
    
    - 특히 이론보다 실무가 앞서 있는 것은 '소프트웨어 설계'와 '소프트웨어 유지보수'이다. 보통의 경우 설계의 이론을 쌓고 이론을 바탕으로 실무에서의 설계나 패턴 등이 정해지는 것 보단, 실무를 경험하고 익숙해지면서 효율적인 설계나 패턴을 이론으로 적립하는 경우가 대부분이라고 한다.
    <br/>
    
    - 결론적으로 '소프트웨어 설계'는 실무 위주로 ! 코드 위주로 배우고 혼자 고민하여 여러 가지 상황을 만들어 설계해보는 것이 필요한 거 같다.
    <br/>
    
#### 01 티켓 판매 애플리케이션 구현하기.
- 상황
    - 소극장 경영중
    - 이벤트 기획 - 무료 초대장 발송
    - 관람객, 당첨자 구분
    - 다른 방식 입장 (티켓으로 교환, 구매)

```java
// 이벤트 당첨자에게 발송되는 초대장 구현
public class Invitation {
	// 공연 관람할 수 있는 초대일자(when) 인스턴스 변수
	private LocalDateTime when;
}



// 공연 관람을 위한 Ticket 클래스
public class Ticket {
	private Long fee;
	
	public Long getFee() {
		return fee;
	}
} 



// 관람객의 소지품 Bag
public class Bag {

	// Bag은 현금과 초대장을 가진 당첨자, 초대장이 없는 관람객 두 가지 상태
	// Bag 인스턴스 생성 시점에 제약 강제하도록 생성자 추가
	public Bag(long amount) {
		this(null, amount);
	}
	
	public Bag(Invitation invitation, long amount) {
		this.invitation = invitation;
		this.amount = amount;
	}

	// 관람객 소지품 현금, 초대장, 티켓
	private Long amount;
	private Invitation invitation;
	private Ticket ticket;
	
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



// 관람객 표현 클래스, 관람객은 소지품을 보관하기 위해 가방을 소지할 수 있다.
public class Audience {
	private Bag bag;
	
	public Audience(Bag bag) {
		this.bag = bag;
	}
	
	public Bag getBag() {
		return bag;
	}
} 
	

	
// 매표소
public class TicketOffice {
	private Long amount;
	private List<Ticket> tickets = new ArrayList<>();
	
	public TicketOffice(Long amount, Ticket ... tickets) {
		this.amount = amount;
		this.tickets.addAll(Arrays.asList(tickets));
	}
	
	public Ticket getTicket() {
		return tickets.remove(0);
	}
	
	public void minusAmount(Long amount) {
		this.amount -= amount;
	}
	
	public void plusAmount(Long amount) {
		this.amount = amount;
	}
	
}


```

    