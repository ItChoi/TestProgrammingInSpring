package com.spring.test.junit5;

public class Study {
	private StudyStatus status = StudyStatus.DRAFT;
	//private StudyStatus status;
	
	private int limit;
	
	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit은 0보다 커야 함.");
		}
		this.limit = limit;
	}
	
	public StudyStatus getStatus() {
		return this.status;
	}

	public int getLimit() {
		return limit;
	}
	
}
