package com.spring.test.Object02;

import java.time.LocalDateTime;

// 상영
public class Screening {
	// 상영 영화
	private Movie movie;
	// 상영 순번
	private int sequence;
	// 상영 시작 시간
	private LocalDateTime whenScreened;
	
	public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
		this.movie = movie;
		this.sequence = sequence;
		this.whenScreened = whenScreened;
	}
	
	// 영화 예매 후 예매 정보 담고있는 인스턴스 생성 후 반환
	// customer: 예매자 정보 / audienceCount: 인원 수
	public Reservation reserve(Customer customer, int audienceCount) {
		return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
	}
	
	// 1인당 예매 요금에 audienceCount를 곱한다.
	private Money calculateFee(int audienceCount) {
		return movie.calculateMovieFee(this).times(audienceCount);
	}

	// 기본 요금 반환
	public Money getMovieFee() {
		return movie.getFee();
	}

	// 순번의 일치 여부 검사
	public boolean isSequence(int sequence) {
		return this.sequence == sequence;
	}

	// 상영 시작 시간 반환
	public LocalDateTime getStartTime() {
		return whenScreened;
	}
	
	
	
	
	
}
