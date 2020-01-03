package com.spring.test.Object02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// public abstract class DiscountPolicy {
public interface DiscountPolicy {
	
	Money calculateDiscountAmount(Screening screening);
	 
	/** 
	// 하나의 할인 정책은 여러 개의 할인 조건을 포함한다.
	private List<DiscountCondition> conditions = new ArrayList<>();
	
	public DiscountPolicy(DiscountCondition ... conditions) {
		this.conditions = Arrays.asList(conditions);
	}
	
	public Money calculateDiscountAmount(Screening screening) {
		for (DiscountCondition each : conditions) {
			if (each.isSatisfiedBy(screening)) {
				return getDiscountAmount(screening);
			}
		}
		
		return Money.ZERO;
	}
	
	abstract protected Money getDiscountAmount(Screening screening);
	*/
}
