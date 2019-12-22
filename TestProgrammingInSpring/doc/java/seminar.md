### 남궁성 카페 객체지향 세미나
- 목차 
    - 객체지향 언어란?
    - 클래스와 객체
    <br/>
    - 변수와 메서드
    - 메서드 오버로딩
    <br/>
    - 생성자
    - 변수의 초기화
    <br/>

#### 객체지향 언어의 특징
- 객체지향언어 
    - 절차지향 + oop개념(규칙 Object Oriented Programming)
    - 설계 중심 (경험 중점, 구현에 중점을 두어 개선해 나가는 과정)
    - 재사용성, 코드의 관리(중복), 신뢰성 높은 프로그램 개발 가능(데이터 보호)

#### 클래스와 객체의 정의와 용도
- 클래스
    - 클래스 정의: 클래스란 객체를 정의해 놓은 것
    - 클래스 용도: 객체를 생성하는데 사용된다.
    <br/>
    - 제품 설계도
    - TV 설계도
    - 붕어빵 기계
    <br/>
- 객체
    - 제품
    - TV
    - 붕어빵    

#### 객체 인스턴스
- 객체와 인스턴스는 다르지만, 처음엔 같다고 생각....

#### 객체의 구성 요소 - 속성과 기능
- 객체는 속성과 기능으로 이루어져 있다.
    - 객체는 속성과 기능의 집합, 속성과 기능을 객체의 멤버라고 한다.
- 속성은 변수로, 기능은 메서드로 정의
    - 클래스를 정의할 때 객체의 속성은 변수로, 기능은 메서드로 정의
    
#### 인스턴스의 생성과 사용
- 인스턴스 생성 방법

#### 클래스의 또 다른 정의
- 설계도
- 변수 + 함수의 결합
- 사용자 정의 타입
    - 개발자가 직접 새로운 타입을 정의 가능
    - 서로 관련된 값을 묶어 하나의 타입으로 정의
    
#### 선언 위치에 따른 변수의 종류
```java
class Variables { // (클래스 영역)
	int iv; // 인스턴스 변수
	static int cv; // 클래스 변수 (static변수, 공유 변수)
	
	void method() { // (메서드 영역)
		int ㅣv = 0; // 지역 변수 
	}
}
```
    
#### 재귀 호출(recursive call)
- 팩토리얼, 제곱, 트리운행, 폴더목록표시 등... 
- 반복문으로 대체가 가능

#### 변수 호출
- 스태틱 영역에서 인스턴스 변수를 사용 못한다. 왜냐하면 스태틱이 사용 가능하더라도 객체가 있을거란 보장이 없다.

#### this
- this()는 생성자, this는 참조변수

#### 변수의 초기화
- iv, cv는 자동적으로 초기화가 된다. 0, "", false 등등등...

#### 멤버 변수의 초기화 시기와 순서
- 초기화 순서
    - cv -> iv // cv가 먼저
    - 자동 -> 간단 -> 복잡  // 자동: 0초기화, 간단: 대입, 복잡: { }
    
#### Stack
``` java
class A {
	public static void main(String[] args) {
		// Stack 쌓기 과정
		// 1. main 메소드가 먼저 스택에 쌓인다.
		// 2. method1이 쌓인다.
		// 3. method1 안에서 method2를 호출하고 있어서 method2가 쌓인다.
		// 4. method3이 쌓인다. 
		
		method1();
		method3();
	}
	
	public method1() {
		method2();
	}
	
	public method2() {
		
	}
	
	public method3() {
		
	}
}
```

#### 상속

#### 클래스간의 관계 - 포함 관계(composite)
- 상속 포함 판단
    - 상속관계: ~은 ~ 이다
    - 포함관계: ~은 ~을 가지고 있다.
    
    
#### super - 참조 변수

#### super() 조상의 생성자
- 조상께 먼저 초기화!
- 자손 클래스의 인스턴스를 생성하면, 자손의 멤버와 조상의 멤버가 합쳐진 하나의 인스턴스가 생성
- 조상의 멤버들도 초기화되어야 하기 때문에 자손의 생성자의 첫 문장에서 조상의 생성자를 호출해야 한다.

```java
class Parent {

	int x;
	int y;

	public Parent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

class Child {

	int z;

	public Child(int x, int y, int z) {
		super(1, 2); // 생략해도 컴파일러가 자동 삽입. -> 생략하면 super() 호출... // 조상꺼는 조상이 하도록 
		this.z = z; // 자기꺼는 자기가 생성
	}
	
}
```

#### 패키지
- 클래스를 묶어놓은 것... 실제로는 폴더... // String은 java.lang에 있는 String.class
- 디폴트 패키지 or unnamed package
- 패키지는 필수가 되서 꼭 넣어야 한다. (맨 첫 줄)
- 클래스 패스(classpath)
    - 내가 만든 클래스 파일이 어디 있는지 경로를 등록!
    

#### 다형성(polymorphism)
- 자식 c = new 부모(); -> 가능
- 부모 c = new 자식(); -> 불가능
- 여러 가지 형태를 가질 수 있는 능력
- 하나의 참조변수로 여러 타입의 객체를 참조할 수 있는 것 즉, 조상 타입의 참조 변수로 자손 타입의 객체를 다룰 수 있는 것

##### 참조 변수의 형변환
- 서로 상속관계에 있는 타입간의 형변환만 가능
- 자손 타입에서 조상 타입으로 형변환하는 경우, 형변환 생략 가능
    - 자손타입 -> 조상타입: 형변환 생략 가능
    - 자손타입 <- 조상타입: 형변환 생략 불가
    
##### instanceof 연산자
- 참조 변수가 참조하는 인스턴스의 실제 타입을 체크하는데 사용
- true / false 리턴
- true일 시 해당 타입으로 형변환 가능
    
##### 참조 변수와 인스턴스 변수의 연결
```java
class Parent {
	int x = 100;
	
	void method() {
		System.out.println("parent Method");
	}
}

class Child extends Parent {
	int x = 200;
	
	void method() {
		System.out.println("child Method");
	}
}

Child c = new Child();
System.out.println(c.x)			// 200
System.out.println(c.method())	// child Method

Parent p = new Child();
System.out.println(p.x)			// 100
System.out.println(p.method())	// child Method

// 멤버 변수는 참조 변수가 해당하는 변수를 불러오지만, 메서드의 경우 오버라이딩된 경우 자식꺼를 타게 된다.
// 여러 종류의 객체를 하나의 배열로 다룰 수 있다.
```






