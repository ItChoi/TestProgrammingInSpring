package com.spring.test.Object11;

import com.spring.test.Object02.Money;

public class TaxablePolicy extends AdditionRatePolicy {
	private double taxRatio;

	public TaxablePolicy(double taxRatio, RatePolicy next) {
		super(next);
		this.taxRatio = taxRatio;
	}
	
	@Override
	protected Money afterCalculated(Money fee) {
		return fee.plus(fee.times(taxRatio));
	}

}
