package com.spring.test.Object11;

import com.spring.test.Object02.Money;

public abstract class AdditionRatePolicy implements RatePolicy {
	private RatePolicy next;
	
	public AdditionRatePolicy(RatePolicy next) {
		this.next = next;
	}
	
	@Override
	public Money calculateFee(Phone phone) {
		Money fee = next.calculateFee(phone);
		return afterCalculated(fee);
	}
	
	abstract protected Money afterCalculated(Money fee); 

}
