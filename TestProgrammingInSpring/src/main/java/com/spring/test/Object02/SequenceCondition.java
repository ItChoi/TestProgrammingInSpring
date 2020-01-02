package com.spring.test.Object02;

// 순번 할인
public class SequenceCondition implements DiscountCondition {

	private int sequence;
	
	public SequenceCondition(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public boolean isSatisfiedBy(Screening screening) {
		
		return false;
	}
	

}
