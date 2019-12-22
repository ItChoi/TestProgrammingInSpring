package com.spring.test.seminar;

import java.util.Arrays;

// Q1. 2차원 좌표상의 한 점(x, y)을 나타내기 위한 Point 클래스를 작성하시오.
// x, y를 멤버로 갖는 클래스를 작성

// 1. 클래스 작성
class Point {
	int x; // 멤버1
	int y; // 멤버2
	
	int[] array = {1, 2, 3}; 
	
	// Q5. x와 y를 초기화 하는 생성자 추가하시오.
	public Point() {
		// 생성자 호출은 첫번째에서만, 주석은 제외
		this(1,1); // 매개 변수가 2개인 생성자 호출 (this가 즉 Point(int x, int y) 
	}
	
	public Point(int x, int y) {
		this.x = x; // 지역 변수 x의 값을 iv x에 넣는다.
		this.y = y;
	}
	
	public void test123() {
		System.out.println("test123!!!");
	}

	// Q9. 나 자신과 obj가 같은지 비교해서 결과를 true, false 반환
	public boolean equals(Object obj) {
		// 1. obj가 가리키는게 Point 객체인지 확인
		// 2. Point 객체가 아니면 false
		// 3. Point 객체면 나 자신과 비교한다.
		if (!(obj instanceof Point)) {
			return false;
		}
		
		Point ppp = (Point) obj;
		
		/*
		 * if (ppp.x == x && ppp.y == y) { return true; } else { return false; }
		 */
		return ppp.x == x && ppp.y == y;
		
		
		/*
		 * boolean returnVal = false;
		 * 
		 * if (obj instanceof Point) { Point obj1 = (Point) obj; obj1.test123(); return
		 * true; }
		 * 
		 * return returnVal;
		 */
	}
	
	
	// Q6. Object의 toString()을 오버라이딩 하시오.
	/*
	 * @Override public String toString() { return "x의 값은 " + x + " 입니다. 그리고 y의 값은 "
	 * + y + " 입니다."; }
	 */
	
	String strArray = "";
	// 배열 찍기
	@Override
	public String toString() {
		
		/*
		 * for (int i = 0; i < array.length; i++) { strArray += array[i] + " "; }
		 */
		
		Arrays.stream(array).forEach(a -> {
			strArray += " " + a + " ";
		});
				
		return "[" + strArray + "]";
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
		
		System.out.println("super class: " + OopTest.class.getSuperclass());
		
		System.out.println("toString overriding: " + pp.toString());
		
		System.out.println("toString array: " + pp.array);
		
		// 상속 관련...
		// A변수에 주소 값이 들어 있고, 그 주소 값을 따라가면 Heap에는 x, y, z 변수가 있다.
		A a = new A();
		
		// 위와 같다.. 
		B b = new B();
		
		// 포함 간련...
		class D {
			A a = new A();
		}
		
		// dClass 주소 값에  힙 영역이 있고 그 영역에 다시 A주소 값의 Heap 영역 존재 (?)
		D dClass = new D();
		
		PointTest pt = new PointTest();
		System.out.println("변수 z: " + pt.toString());
		
		TestChild tc = new TestChild(1, 2, 3);

		
		Point polymorphism1 = new Point();
		Point3D polymorphism2 = new Point3D();
		Point pTest = new Point();
		
		System.out.println("다형성 테스트1: " + pTest.equals(polymorphism1));
		System.out.println("다형성 테스트2: " + pTest.equals(polymorphism2));
		
		
	}
	
}


class A {
	int x;
	int y;
	int z;
}

class B extends C {
	int z;
}

class C {
	int y;
	int z;
}

//Q7. Point를 상속받는 Point3D 클래스를 작성하시오.
// 그리고 Point3D에 멤버 변수 z를 추가하시옹.
// Q8. Point3D에 toString()을 적절히 오버라이딩하시오.
class Point3D {
	public int z = 1;
	
}

class PointTest extends Point3D {
	public int z = 2;
	
	@Override
	public String toString() {
		return "부모z의 값은 " + super.z + " 입니다 그리고 나의 z의 값은 " + z + " 입니다!";
	}
}

/*
 * class TestParent { int x; int y;
 * 
 * public TestParent() {
 * 
 * }
 * 
 * public TestParent(int x, int y) { this.x = x; this.y = y; }
 * 
 * 
 * }
 * 
 * class TestChild extends TestParent { int z;
 * 
 * public TestChild() {
 * 
 * }
 * 
 * public TestChild(int x, int y, int z) { this.x = x; this.y = y; }
 * 
 * }
 */

class TestParent {
	int x;
	int y;
	
	public TestParent() {
		
	}
	
	public TestParent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

class TestChild extends TestParent {
	int z;
	
	public TestChild() {
		
	}

	public TestChild(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	
}

