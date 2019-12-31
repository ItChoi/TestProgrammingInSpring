package com.spring.test.Object01;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

public class 객체지향설계연습01_카페 {
	
	// 객체지향 설계 첫 번째 연습 (카페)
	/**
	 * - 상황
	 * 1. 카페 주문은 직원 및 키오스크 - 둘 다 이용
	 * 2. 카페에서 메뉴를 주문 전 웨이팅이 있을 수 있다.
	 * 3. 메뉴 주문 시 음료 테이크아웃 여부 결정
	 * 4. 메뉴 주문 후 음료 만드는 시간이 존재하고 손님은 진동벨을 통해 음료를 가져간다.
	 * 
	 * 
	 * - 로직
	 * 1. 손님이 카페에 들어간다.
	 * 2. 순번을 확인한다. (대기열)
	 * 3. 직원 주문 대기열, 키오스크 대기열을 확인 후 적은 곳에 줄을 선다. 
	 * 4. 메뉴를 확인한다.
	 * 5. 메뉴를 고른다.
	 * 6. 주문을 한다(키오스크, 직원)
	 * 7. 음료를 받고 자리 착석 또는 카페를 나간다.
	 * 8. 자리 착석 시 테이블 인덱스와 그룹(사람1~다수)인덱스를 매칭시켜 착석 인원 확인
	 * 9. 착석 인원이 자리 떠날 시 테이블 자리수가 늘어나고, 그 그룹은 삭제된다.
	 *
	 * 
	 * - 필요 객체
	 * 1. 카페 (SanghyunCafe class)
	 * 2. 메뉴 (Menu class)
	 * 3. 각 음료 (설계 필요 - beverage interface -> (coffee, smoothie, juice, iceCream, bread)) 
	 * 4. 직원 (Employee interface -> (manager, staff, partTime)
	 * 5. 키오스크 (Kiosk class)
	 * 6. 주문 (직원 또는 키오스크 (설계 필요 - order interface -> (Employee, Kiosk)))
	 * 7. 손님 (CustomerGroup class)
	 */
	
	private static final Logger log = LoggerFactory.getLogger(객체지향설계연습01_카페.class);
	
	public static void main(String[] args) {
		
		SangHyunCafe cafe = new SangHyunCafe();
		
		
		
		
		
		
		
	}
	
}

@Getter
@Setter
class SangHyunCafe {
	/**
	 * - 행위
	 * 1. 음료 판매
	 * 
	 */
	
	private static final int AVAILABLE_CUSTOMER_NUMBER = 20;
	private int inCafePeople = 0;
	
	private Employee employee;
	
	
	
	
}


@Getter
@Setter
class Tables {
	/**
	 * - 상황
	 * 1. 테이블 하나 당 2인이 앉을 수 있다.
	 * 2. 음료를 기다리는 손님 또는 자리 착석 손님이 자리에 앉는다.
	 * 3. 자리를 떠난다.
	 * 
	 * - 로직
	 * 1. 인원 수에 따라 테이블 남은 개수가 있는지 확인
	 * 2. 테이블 인덱스를 지정하여 몇명이 앉았는지 추가
	 * 3. 나갈 때 테이블 인덱스에 일부 / 전체가 나가는지 확인하여 계산
	 * 
	 * - 행위
	 * 1. 자리 앉는다.
	 * 2. 자리 일어선다.
	 */

	private int tableNumber;
	// 테이블 번호, 명수
	private Map<Integer, Integer> tables = new HashMap<>();
	
	public void sitDownGroup() {
		
	}
	
	public void standUpGroup() {
		
	}
	
	
	
	
}


@Getter
@Setter
class CustomerGroup {
	/**
	 * - 상황
	 * 1. 카페에 들어간다.
	 * 2. 줄이 있으면 기다린다.
	 * 3. 주문한다.
	 * 4. 상품이 나올때까지 기다린다.
	 * 
	 * - 로직
	 * 1. 카페에 들어간다.
	 * 2. 카페에 들어온 시점에 같이 들어온 지인을 묶어 그룹에 넣어준다.
	 * 3. 줄이 없으면 바로 주문, 줄이 있으면 직원 & 키오스크 대기열이 짧은 곳에서 줄을 선다.
	 * 4. 자기 차례가 되면 주문한다.
	 * 5. 테이크 아웃 여부 결정
	 * 6. 착석 또는 카페 나감
	 */

	// 테이블 번호, 명수, 테이크아웃 상태 
	private Map<Integer, Map<Integer, String>> customerGroups = new HashMap<>();
	private TakeOutStatus takeOutStatus;

	// 카페 입장
	public void inCafe() {
		
		
	}
	
	// 카페 퇴장
	public void outCafe() {
		
	}
	
	// 웨이팅
	public void wating() {
		
	}
	
	// 주문
	public void beverageOrder() {
		// 주문 - 직원 or 키오스크
	}
	
}


@Getter
enum TakeOutStatus {
	Y("1", true),
	N("0", false);
	
	// 변수
	private String stringTakeOut;
	private boolean booleanTakeOut;

	// 생성자
	private TakeOutStatus(String stringTakeOut, boolean booleanTakeOut) {
		this.stringTakeOut = stringTakeOut;
		this.booleanTakeOut = booleanTakeOut;
	}
	
}


@Getter
@Setter
class Menu {
	
}


interface Beverage {
	Beverage getBeverage();
	
}


@Getter
@Setter
class Coffee implements Beverage {

	
	
	@Override
	public Beverage getBeverage() {
		
		return null;
	}
	
}


@Getter
@Setter
class Smoothie implements Beverage {

	
	
	@Override
	public Beverage getBeverage() {
		
		return null;
	}
	
}


@Getter
@Setter
class Juice implements Beverage {

	
	
	@Override
	public Beverage getBeverage() {
		
		return null;
	}
	
}


@Getter
@Setter
class IceCream {
	
}


@Getter
@Setter
class Bread {
	
}


interface Employee {
	Beverage serveBeverage();
}


@Getter
@Setter
class Manager implements Employee {
	private Beverage beverage;

	@Override
	public Beverage serveBeverage() {
		
		return beverage.getBeverage();
	}
	
}


@Getter
@Setter
class Staff implements Employee {
	private Beverage beverage;
	
	@Override
	public Beverage serveBeverage() {
		
		return null;
	}
}


@Getter
@Setter
class PartTime implements Employee {
	private Beverage beverage;
	
	@Override
	public Beverage serveBeverage() {
		
		return null;
	}
}


@Getter
@Setter
class Kiosk {
	
}


interface Order {
	
}