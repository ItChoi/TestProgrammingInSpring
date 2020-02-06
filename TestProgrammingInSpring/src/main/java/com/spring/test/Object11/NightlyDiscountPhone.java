package com.spring.test.Object11;

import java.time.Duration;

import com.spring.test.Object02.Money;

public class NightlyDiscountPhone extends Phone {
	public NightlyDiscountPhone(RatePolicy ratePolicy) {
		super(ratePolicy);
		// TODO Auto-generated constructor stub
	}
	private static final int LATE_NIGHT_HOUR = 22;
	
	private Money nightlyAmount; // 심야 할인 요금제 단위 금액
	private Money regularAmount; // 일반 요금제 단위 금액
	private Duration seconds; // 단위 시간
	
	
}
