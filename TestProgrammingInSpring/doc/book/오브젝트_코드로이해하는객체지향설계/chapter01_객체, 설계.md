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
    <br/>
    - 관람객은 가방이 있다고 가정!
    <br/>


```java
// 이벤트 당첨자에게 발송되는 초대장 구현
public class Invitation {
	// 공연 관람할 수 있는 초대일자(when) 인스턴스 변수
	private LocalDateTime when;
	
	public LocalDateTime getWhen() {
		return when;
	}

	public void setWhen(LocalDateTime when) {
		this.when = when;
	}
}



// 공연 관람을 위한 Ticket 클래스
public class Ticket {
	private Long fee;
	
	public Long getFee() {
		return fee;
	}
	
	public void setFee(Long fee) {
		this.fee = fee;
	}
} 



// 관람객의 소지품 Bag
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



// 판매원, 판매원은 자신이 일하는 매표소를 알고 있어야 한다.
public class TicketSeller {
	private TicketOffice ticketOffice;
	
	public TicketSeller(TicketOffice ticketOffice) {
		this.ticketOffice = ticketOffice;
	}
	
	public TicketOffice getTicketOffice() {
		return ticketOffice;
	}
}



// 대망의 소극장
public class Theater {
	private TicketSeller ticketSeller;
	
	public Theater(TicketSeller ticketSeller) {
		this.ticketSeller = ticketSeller;
	}
	
	public void enter(Audience audience) {
		if (audience.getBag().hasInvitation()) {
			Ticket ticket = ticketSeller.getTicketOffice().getTicket();
			audience.getBag().setTicket(ticket);
		} else {
			Ticket ticket = ticketSeller.getTicketOffice().getTicket();
			audience.getBag().minusAmount(ticket.getFee());
			ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
			audience.getBag().setTicket(ticket);
		}
	}
}



public class MainClass {
	Bag bag = new Bag(10000L);
	Audience audience = new Audience(bag);
	
	Ticket ticket = new Ticket();
	ticket.setFee(3000L);
	TicketOffice ticketOffice = new TicketOffice(5000L, ticket);
	TicketSeller ticketSeller = new TicketSeller(ticketOffice);
	Theater theater = new Theater(ticketSeller);
	
	theater.enter(audience);
}
```

- 로직
    - 소극장은 관람객 가방 안에 초대장 유무 확인
    - 당첨자가 아니라면 티켓 구매해야 한다.
    - 가방에서 티켓 금액 차감 후 매표소 금액증가
    - 가방안에 티켓 넣어줌
    <br/>

##### 무엇이 문제인가?
- 소프트웨어 모듈이 가져야하는 세 가지 기능
    - 모듈은 크기와 상관없이 패키지나 클래스, 라이브러리와 같이 프로그램을 구성하는 것을 의미.
    - 기능(1). 실행 중 제대로 동작 
      - 모듈의 존재 이유
    - 기능(2). 변경을 위해 존재
      - 대부분의 모듈은 생명주기 동안 변경 작업이 일어나기 때문에 쉽게 변경 가능해야함.
      - 결합도를 줄이고 중복 코드 개선..
    - 기능(3). 다른 개발자가 코드로 의사소통이 가능하도록..
      - 코드를 쉽게 이해하고 읽을 수 있게 해야한다.
      <br/>
      
- 위에 코드들은 기능(1)은 만족하지만, 아쉽게 기능(2)와 기능(3)을 만족시키진 못한다.
    - 원인
      - 클래스가 각자의 역할에 충실하지 못하다. 예를 들어 돈을 가방에 꺼내어 매표소에 지불하는 것은 관람객의 역할, 티켓을 뽑아 관람객에게 건내고 돈을 넣는 것은 직원의 역할이다. 그렇지 못하기에 이 코드를 이해하기 어렵다.
      - 하나의 클래스나 메서드에 많은 내용이 담겨 있어 모든 사실을 기억하기 어렵다.
      - 하나의 클래스를 변경하면, 이 클래스를 참조하고 있던 코드도 같이 변경해 불필요한 작업이 생겨난다.   
        - 객체 사이의 의존성(dependency)을 완전히 없애는 것은 정답이 아니다. 따라서 최소한의 의존성만을 유지하는 것이 중요하다.
        <br/>
        
- 해결!!!
    - 관람객과 판매원의 역할을 극장이 알아야 할 필요가 없다. 극장은 단지 관람객을 입장시키기만 하면 된다. 
    - 관람객의 역할, 판매원의 역할을 구분하여 자율적인 존재로 만들자!
    <br/>
    
    - 1단계: Theater의 enter 메서드에서 TicketOffice에 접근하는 모든 코드를 TicketSeller 내부로 숨기는 것이다.
      - TicketSeller에 sellTo 메서드 추가하고, Theater에 있던 로직을 이 메서드로 옮기자.
      <br/>
      
    - 2단계: 관람객의 bag에 접근하는 것은 직원이 아니라 본인이어야 한다. 캡슐화를 해보자. 
      - Bag에 접근하는 모든 로직을 Audience로 숨기기 위해 buy 메서드 추가
      - TicketSeller의 sellTo 메서드에서 getBag() 접근 부분을 buy 메서드로 옮기자.
      <br/>
      
    - 3단계: 관람객은 스스로 Bag에서 티켓을 확인하고, 직원은 Audience의 인터페이스에만 의존하도록 해보자.
      - TicketSellet가 buy 메서드를 호출하도록 변경!
      
      
	```java
	//관람객 표현 클래스, 관람객은 소지품을 보관하기 위해 가방을 소지할 수 있다.
	public class Audience {
		
		private Bag bag;
		
		public Audience(Bag bag) {
			this.bag = bag;
		}
		
		/* 2단계
		 * public Bag getBag() { return bag; }
		 */
		
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
		
	} 
	
	
	
	//대망의 소극장
	public class Theater {
		private TicketSeller ticketSeller;
		
		public Theater(TicketSeller ticketSeller) {
			this.ticketSeller = ticketSeller;
		}
		
		public void enter(Audience audience) {
			
			/** 1단계
			if (audience.getBag().hasInvitation()) {
				Ticket ticket = ticketSeller.getTicketOffice().getTicket();
				audience.getBag().setTicket(ticket);
			} else {
				Ticket ticket = ticketSeller.getTicketOffice().getTicket();
				audience.getBag().minusAmount(ticket.getFee());
				ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
				audience.getBag().setTicket(ticket);
			}
			*/
			
			ticketSeller.sellTo(audience);
			
		}
		
	}
	
	
	
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
			ticketOffice.plusAmount(audience.buy(ticketOffice.getTicket()));
			
		}
	}
	
	```


- 개선된 점
    - 관람객과 직원은 내부 구현을 외부에 노출하지 않고 역할에 책임을 가졌다.
    - 관람객이나 직원의 코드가 변경되도 극장의 코드를 변경할 필요가 없다.
    - 객체의 자율성을 높임으로써 이해하기 쉽고 유연한 설계를 얻었다.    