package com.spring.test.Object02;

public interface DiscountCondition {
	// 할인 가능하면 true 리턴
	boolean isSatisfiedBy(Screening screening);
}
