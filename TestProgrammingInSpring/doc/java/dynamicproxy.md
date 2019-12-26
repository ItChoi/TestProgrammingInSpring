### 다이나믹 프록시 
- 리플렉션의 일부..
- 동적으로 런타임 시 프록시를 생성하게 하는 것: 다이나믹 프록시

#### 프록시 패턴
![프록시 패턴](file:///C:/Study/Project/TestProgrammingInSpring/TestProgrammingInSpring/src/main/webapp/resources/images/java/dynamicproxy.PNG)

- 프로그래밍에서의 프록시
    1. 프록시, 리얼 서브프로젝트는 똑같은 인터페이스(서브젝트)를 구현하고 있다.
    2. 프록시가 리얼 서브젝트를 참조
    3. 클라이언트는 공유하고 있는 인터페이스 타입(서브젝트)으로 사실상 프록시를 사용.
    <br/>
    4. 즉, 클라이언트는 프록시를 거쳐 리얼 서브젝트를 사용하고 있으며, 여기서 프록시는 리얼 서브젝트에 보낼 수도 있고, 클라이언트에게 되돌려 보낼수도 있다.
    5. 리얼 서브젝트는 원래 자기가 할 일만 ! (SRP: Single Responsibility Principle)
    6. 리얼 서브젝트에서의 부가적인 기능을 추가하면 코드가 지저분해지기 떄문에 프록시에 그런 코드들을 추가하여 SRP를 유지시킨다.
    <br/>
    7. 결론: 매번 프록시가 필요할 때 마다 클래스를 만드는 것이 아니라, 동적으로 런타임에 생성해내는 방법을 다이나믹 프록시라고 부르고, 자바 리플렉션 패키지에서 제공하는 기능이 있다.

#### 다이나믹 프록시 (자바가 제공하는)
- 런타임에 (애플리케이션이 실행되는 도중에) 특정 인터페이스들을 구현하는 클래스 또는 인스턴스를 만드는 기술을 다이나믹 프록시라 한다.
- 이번에는 인스턴스 만드는 것!
- 다이나믹 프록시를 사용해서 프록시 인스턴스를 어떻게 만드는가 알아보자.

##### 다이나믹 프록시 (인터페이스 기반)

    ```java
    // 제약사항1. 다이나믹 프록시가 클래스 기반의 프록시는 못만든다. 즉 두 번째 인자의 클래스 배열안의 값이 반드시 인터페이스여야 한다.
    // 코드가 실행되는 시점에 만들 수 있다. 첫 번째 인자는 만들어낼 클래스 로더를 주고, 두 번째 인자는 인터페이스의 배열을 준다. 어떤 인터페이스 타입의 프록시인지 알려주기 위함, 
    // 마지막 인자는 invocationHanlder인데, 이 프록시의 메소드가 호출될 때 호출을 어떻게 처리할 것인지에 대한 것을 기술! 
    // 오브젝트 타입을 리턴하기 떄문에 원하는 타입으로 캐스팅이 필요. 
    BookService bs = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, new InvocationHandler() {
    // 리얼 서브젝트
    BookService bookService = new DefaultBookService();

	// 프록시로 메서드 호출이 들어왔을 때 그 메소드 호출을 어떻게 처리할 것인지는, 인자 method를 통해..., proxy 생성해준 것이 인자 proxy!
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
    	// return 하기 전에 부가적인 기능을 넣어준다.
    	System.out.println("부가적인 기능1");
    	// method.invoke(리얼서브젝트, args); 
    	Object invoke = method.invoke(bookService, args);
    	System.out.println("부가적인 기능2");
    	
    	// 만약 메소드1, 메소드2 중에 부가적인 기능을 메소드1에만 적용하고 싶다면 코드 추가 필요
    	if (method.getName().equals("method1") {
    		// 부가적인 코드 추가~
    		return method.invoke(bookService, args);
    	}
    	
    	return invoke;
    }
    
    });
    // 위 처럼 프록시를 사용하면, 매번 클래스를 만드는 수고는 덜어지지만, 문제는 invocationHandler 자체가 유연하지가 않다. 코드가 길어지거나, 프록시를 더 추가해야될지도 모른다. 그래서 spring aop가 인터페이스로 만든 게 있다. spring aop를 proxy 기반의 aop라 부른다.
    ```
    

    
##### 다이나믹 프록시 (클래스 기반 - 인터페이스가 없는 경우)  
- 2가지 라브러리로 만들어 볼 것임(1: CGlib, 2: ByteBuddy)

- CGlib
    - 스프링, 하이버네이트에서도 사용
    - 하위버전 호환이 좋지 않아서 내장형태로 제공하기도 한다.
    - 핵심 클래스 Enhancer
   <br/>
    - dependency 추가
     
   ```java
   // 프록시 객체 호출 마다 어떤 일을 하는지를 알려주는 handler를 넘겨준다.
   // 핸들러는 여러 가지 방법으로 만들 수 있다.
   // 1. MethodInterceptor
   MethodInterceptor handler = new MethodInterceptor() {
   BookService bookService = new BookService();
   
   	@Override
   	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws ... {
   		// 부가적인 코드 1
   		Object invoke = method.invoke(bookService, args);
   		// 부가적인 코드 2
   		
   		// 특정 메소드만 추가
   		if (method.getName().equals("method1") {
   		
   		}
   		
   		return invoke
   	};
   }
   BookService bs = (BookService) Enhancer.create(BookService.class, handler);
   
   ```
   
    

- ByteBuddy (바이트 코드 조작 때도 사용된다.)
    - 서브 클래스를 만드는 방법에 단점은, 상속을 사용하지 못하는 경우 만들 구 없다.
    - 인터페이스가 있을 경우 인터페이스 기반으로 사용!  
    - dependency 추가
    - 스프링에서 버전관리를 해주기에, 버전 명시를 하지 않아도 되고 해도 된다.
    
    ```java
    // 인스턴스를 바로 만들지 않고 클래스를 만들어야 한다.
    // BookService의 서브 클래스를 만들고, 로딩을 BookService를 읽어온 클래스로더로 할 거고, 로딩된 것을 가져오는 것!
    Class<? extends BookService> proxyClass = new ByteBudy().subClass(BookService.class)
    // 부가적인 기능 추가하고 싶다면,
    .method(named("method1")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
    	BookService bookService = new BookService();
    	
    
    	@Override
    	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	return method.invoke(bookService, args);
    	}
    ))
   	.make().load(BookService.class.getClassLoader()).getLoaded();
    	
    	// 해당 클래스의 인스턴스를 만드는 방법
    	proxyClass.getConstructor(null).newInstance();
    ```



#### 다이나믹 프록시
- 런타임에 인터페이스 또는 클래스의 프록시 인스턴스 또는 클래스를 만들어 사용하는 기법




  