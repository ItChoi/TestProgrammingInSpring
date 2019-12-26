package com.spring.test.junit5;

public class Study {
	private StudyStatus status = StudyStatus.DRAFT;
	//private StudyStatus status;
	
	private int limit;
	
	public Study(int limit) {
		this.limit = limit;
	}
	
	public StudyStatus getStatus() {
		return this.status;
	}

	public int getLimit() {
		return limit;
	}
	
}
