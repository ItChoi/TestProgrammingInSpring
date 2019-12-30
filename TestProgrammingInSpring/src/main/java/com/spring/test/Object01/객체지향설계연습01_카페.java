package com.spring.test.Object01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 *
	 * 
	 * - 필요 객체
	 * 1. 카페 (SanghyunCafe class)
	 * 2. 메뉴 (Menu class)
	 * 3. 각 음료 (설계 필요 - beverage interface -> (coffee, smoothie, juice, iceCream, bread)) 
	 * 4. 직원 (Employee interface -> (manager, staff, partTime)
	 * 5. 키오스크 (Kiosk class)
	 * 6. 주문 (직원 또는 키오스크 (설계 필요 - order interface -> (Employee, Kiosk)))
	 * 
	 */
	
	private static final Logger log = LoggerFactory.getLogger(객체지향설계연습01_카페.class);
	
	public static void main(String[] args) {
		
		
	}
	
}

class SangHyunCafe {
	/**
	 * - 행위
	 * 1. 손님 입장
	 * 2. 손님 퇴장
	 * 
	 */
	
	
	
	
}

class TablesAndChairs {
	/**
	 * - 상황
	 * 1. 테이블 하나당 놓을 수 있는 의자의 개수는 2 ~ 4개 
	 * 2. 테이블 2개가 붙을 경우 의자의 개수는 4 ~ 6개
	 * 3. 손님은 카페를 들어온 시점에 같이 들어온 인원을 기준으로 자리에 착석 또는 웨이팅한다.
	 * 4. 음료가 나오기 전까지 착석하는 손님, 자리 착석 손님으로 나뉜다.
	 * 
	 * - 로직
	 * 1. 인원 수에 따라 테이블 및 의자 계산 남은 개수 확인
	 * 2. 테이블 인덱스를 지정하여 몇명이 앉았는지 추가
	 * 3. 나갈 때 테이블 인덱스에 일부 / 전체가 나가는지 확인하여 계산
	 * 
	 * 
	 * - 행위
	 * 1. 자리 착석
	 * 2. 자리 나감
	 */

}

class Menu {
	
}

interface Beverage {
	
}

class Coffee {
	
}

class Smoothie {
	
}

class Juice {
	
}

class IceCream {
	
}

class Bread {
	
}

interface Employee {
	
}

class Manager {
	
}

class Staff {
	
}

class PartTime {
	
}

class Kiosk {
	
}

interface Order {
	
}