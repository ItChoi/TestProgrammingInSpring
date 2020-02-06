package com.spring.test.Object11;

import java.time.Duration;

import com.spring.test.Object02.Money;

public class RegularPhone extends Phone {
	public RegularPhone(RatePolicy ratePolicy) {
		super(ratePolicy);
		// TODO Auto-generated constructor stub
	}
	private Money amount; // 단위 금액
	private Duration seconds; // 단위 시간
	

	
}
