package com.spring.test.Object02;

import java.time.DayOfWeek;
import java.time.LocalTime;

// 기간 할인
public class PeriodCondition implements DiscountCondition {
	// 요일
	private DayOfWeek dayOfWeek;
	// 시작 시간
	private LocalTime startTime;
	// 종료 시간
	private LocalTime endTime;
	
	public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public boolean isSatisfiedBy(Screening screening) {
		return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
				startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0 &&
				endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
	}
	
}
