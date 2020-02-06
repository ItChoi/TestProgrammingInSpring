package com.spring.test.Object11;

import com.spring.test.Object02.Money;

public class RateDiscountablePolicy extends AdditionRatePolicy {
	private Money discountAmount;

	public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
		super(next);
		this.discountAmount = discountAmount;
	}
	
	@Override
	protected Money afterCalculated(Money fee) {
		return fee.minus(discountAmount);
	}

}
