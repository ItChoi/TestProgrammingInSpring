package com.spring.test.Object02;

// public class NoneDiscountPolicy extends DiscountPolicy {
public class NoneDiscountPolicy implements DiscountPolicy {

	@Override
	public Money calculateDiscountAmount(Screening screening) {
		return Money.ZERO;
	}
	
	/**
	@Override
	protected Money getDiscountAmount(Screening screening) {
		return Money.ZERO;
	}
	*/
	

}
