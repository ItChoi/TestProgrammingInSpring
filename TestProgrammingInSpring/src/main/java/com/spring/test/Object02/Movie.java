package com.spring.test.Object02;

import java.time.Duration;

public class Movie {
	private String title;
	private Duration runningTime;
	private Money fee;
	private DiscountPolicy disCountPolicy;
	
	public Movie(String title, Duration runningTime, Money fee, DiscountPolicy disCountPolicy) {
		this.title = title;
		this.runningTime = runningTime;
		this.fee = fee;
		this.disCountPolicy = disCountPolicy;
	}
	
	public Money getFee() {
		return fee;
	}
	
	public Money calculateMovieFee(Screening screening) {
		// 할인 금액이 0원이라는 것을 DiscountPolicy가 결정해야 하기 떄문에
		// 여기에 if문을 추가하는 것은 협력의 설계 측면에서 좋지 않은 경우이다.
		// if (disCountPolicy == null) {
		// 	return fee;
		// }
		
		return fee.minus(disCountPolicy.calculateDiscountAmount(screening));
	}
	
	public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
		this.disCountPolicy = discountPolicy;
	}
	
}
