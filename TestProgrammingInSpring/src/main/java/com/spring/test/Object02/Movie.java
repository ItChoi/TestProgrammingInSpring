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
		return fee.minus(disCountPolicy.calculateDiscountAmount(screening));
	}
	
	
}
