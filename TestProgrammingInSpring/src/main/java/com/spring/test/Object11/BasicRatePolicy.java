package com.spring.test.Object11;

import com.spring.test.Object02.Money;

public abstract class BasicRatePolicy implements RatePolicy {
	
	@Override
	public Money calculateFee(Phone phone) {
		Money result = Money.ZERO;
		
		for (Call call : phone.getCalls()) {
			result.plus(calculateCallFee(call));
		}
		
		return result;
	}
	
	protected abstract Money calculateCallFee(Call call);
	
}
