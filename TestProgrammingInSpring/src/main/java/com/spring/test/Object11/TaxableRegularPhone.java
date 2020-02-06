package com.spring.test.Object11;

import java.time.Duration;

import com.spring.test.Object02.Money;

public class TaxableRegularPhone extends RegularPhone {
	public TaxableRegularPhone(RatePolicy ratePolicy) {
		super(ratePolicy);
		// TODO Auto-generated constructor stub
	}


	private double taxRate;

	
	@Override
	public Money calculateFee() {
		Money fee = super.calculateFee();
		return fee.plus(fee.times(taxRate));
	}


}
