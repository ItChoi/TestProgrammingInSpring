package com.spring.test.Object10;

import java.time.Duration;

import com.spring.test.Object02.Money;

public class RegularPhone extends Phone {
	private Money amount; // 단위 금액
	private Duration seconds; // 단위 시간
	
	public RegularPhone(Money amount, Duration seconds, double taxRate) {
		super(taxRate);
		this.amount = amount;
		this.seconds = seconds;
	}

	public Money getAmount() {
		return amount;
	}

	public Duration getSeconds() {
		return seconds;
	}
	
	
	@Override
	protected Money calculateCallFee(Call call) {
		return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
	}
	
}
