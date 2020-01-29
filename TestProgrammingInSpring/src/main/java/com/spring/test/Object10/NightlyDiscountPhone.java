package com.spring.test.Object10;

import java.time.Duration;

import com.spring.test.Object02.Money;

public class NightlyDiscountPhone extends Phone {
	private static final int LATE_NIGHT_HOUR = 22;
	
	private Money nightlyAmount; // 심야 할인 요금제 단위 금액
	private Money regularAmount; // 일반 요금제 단위 금액
	private Duration seconds; // 단위 시간
	
	public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds, double taxRate) {
		super(taxRate);
		this.nightlyAmount = nightlyAmount;
		this.regularAmount = regularAmount;
		this.seconds = seconds;
	}

	
	@Override
	protected Money calculateCallFee(Call call) {
		if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
			return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
		} else {
			return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
		}
	}
	
}
