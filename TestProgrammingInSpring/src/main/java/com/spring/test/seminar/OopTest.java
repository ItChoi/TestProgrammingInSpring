package com.spring.test.seminar;

// Q1. 2차원 좌표상의 한 점(x, y)을 나타내기 위한 Point 클래스를 작성하시오.
// x, y를 멤버로 갖는 클래스를 작성

// 1. 클래스 작성
class Point {
	int x; // 멤버1
	int y; // 멤버2
	
	// Q5. x와 y를 초기화 하는 생성자 추가하시오.
	public Point() {
		// 생성자 호출은 첫번째에서만, 주석은 제외
		this(1,1); // 매개 변수가 2개인 생성자 호출 (this가 즉 Point(int x, int y) 
	}
	
	
	public Point(int x, int y) {
		this.x = x; // 지역 변수 x의 값을 iv x에 넣는다.
		this.y = y;
	}
	
	
	
	// Q2. 두 점의 거리를 계산해서 double로 반환하는
	// getDistance() 메서드를 작성하시오.
	// 출력 메서드 이름 (입력)
	/**
	 * Q2. 두 점의 거리를 계산해서 double로 반환하는
	 * getDistance() 메서드를 작성하시오.
	*/
	static double getDistance(int x1, int y1, int x2, int y2) {
		int a = x1 - x2;
		int b = y1 - y2;
		double d = Math.sqrt(a*a+b*b);
	 
		return d;
	}
	
	static double getDistance(Point p1, Point p2) {
		int a = p1.x - p2.x;
		int b = p1.y - p2.y;
		double d = Math.sqrt(a*a+b*b);
	 
		return d;
	}
	
	// 중복 제거
	static double getDistance2(Point p1, Point p2) {
		// Q3. getDistance(int x1, int y1, int x2, int y2)를
		// 호출하도록 변경하시오. (코드 중복의 제거
		
		return getDistance(p1.x, p1.y, p2.x, p2.y);
	}
	
	// 매개 변수 하나짜리로 만들어보기.
	int x1;
	int y1;
	int x2;
	int y2;
	double getDistance3(Point p) {
		return getDistance(p.x1, p.y1, p.x2, p.y2);
	}
	
	double getDistance4(Point p) {
		return getDistance(x, y, p.x, p.y);
	}
	
}

public class OopTest {
	public static void main(String[] args) {
		// 2. 객체 생성
		Point p = new Point();
		
		// 두 점 (0,0)과 (1,1)의 거리를 구한다.
		// double d = p.getDistance(1, 1, 1, 1);
		double d = Point.getDistance(1, 1, 1, 1);
		System.out.println(d);
		
		Point p1 = new Point();
		Point p2 = new Point();
		
		p1.x = 1;
		p2.x = 2;
		p1.y = 3;
		p2.y = 4;
		double dd = Point.getDistance(p1, p2);
		System.out.println(dd);

		
		// 3. 객체 사용 - 객체가 가진 멤버(변수 + 메서드)를 사용
		p.x = 3;
		p.y = 5;
		System.out.println("x= " + p.x);
		System.out.println("y= " + p.y);
		
		Point pp = new Point();
		pp.x1 = 1;
		pp.y1 = 1;
		pp.x2 = 1;
		pp.y2 = 1;
		double ddd = pp.getDistance3(pp);
		System.out.println("test3: " + ddd);
		
		
	}
}
