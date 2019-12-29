package com.spring.test.Object01;

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
	
	public static void main(String[] args) {
		
	}
	
}

class SangHyunCafe {
	
}


