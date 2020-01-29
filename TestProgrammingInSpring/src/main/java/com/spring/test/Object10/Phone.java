package com.spring.test.Object10;

import java.util.ArrayList;
import java.util.List;

import com.spring.test.Object02.Money;

public abstract class Phone {
	private double taxRate;
	private List<Call> calls = new ArrayList<>();
	
	
	
	public Phone(double taxRate) {
		this.taxRate = taxRate;
	}

	public Money calculateFee() {
		Money result = Money.ZERO;
		
		for (Call call : calls) {
			result = result.plus(calculateCallFee(call));
		}
		
		return result.plus(result.times(taxRate));
	}
	
	abstract protected Money calculateCallFee(Call call);
}
