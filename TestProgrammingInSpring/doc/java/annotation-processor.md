### 애노테이션 프로세서
#### Lombok(롬복)은 어떻게 동작하나?
- @Getter, @Setter, @Builder, @EqualsAndHashCode, ... 제공
- dependency 롬복 추가
- 롬복은 자바가 제공하는 애노테이션 프로세서 기반으로 만들어져있다.

##### 애노테이션 프로세서 
- 컴파일할 때 끼어들어서 특정 애노테이션 붙어있는 코드를 참조해서 또 다른 코드(자바 코드, 문서 등)를 만들어내는 기능!
- 문제는 애노테이션 프로세서는 애노테이션이 붙어있는 클래스의 정보를 트리 구조(AST: abstract syntax tree)로 참조(수정불가)할 수 있는데, 수정이 된다. 즉 바이트 코드가 바뀐 것 처럼 들어온다. 즉 논란 거리가 있다
    - 논란거리
      - 공개된 API가 아닌 컴파일러 내부 클래스를 사용하여 기존 소스 코드를 조작한다. (자바 버전 업으로 인해 내부 클래스의 변경 가능성)
      - 특히 이클립스의 경우 java agent를 사용하여 컴파일러 클래스까지 조작하여 사용한다. 해당 클래스들 역시 공개된 API가 아니라서 버전 호환성 문제가 생길 수 있고, 언제라도 발생해도 이상하지 않은 상황.
      - 그럼에도 편리해서 널리 쓰이고 있으며, 대안이 몇 가지 있지만, 롬복의 모든 기능과 편의성을 대체하지 못한다.
        - AutoValue, Immutables
         
- Processor 인터페이스
    - 여러 라운드에 거쳐 소스 및 컴파일된 코드 처리 가능
    <br/>
- 유틸리티
    - Javapoet: 소스코드 생성 유리틸리티
    - AutoService: 서비스 프로바이더 레지스트리 생성기
    
```java
// *** 프로젝트1
public class App {
	public static void main(String[] args) {
		Moja moja = new MojaMoja();
		
		MojaMoja magicMoja= new MojaMoja();
		System.out.println(moja.pullOut());
	}
}

@Magic
public interface Moja {
	public String pullOut();
}



// *** 프로젝트2

// 컴파일 타임에 쓰고, 바이트 코드에 필요 없다. 그렇다면 SOURCE가 적당!
// 클래스까지 갈 필요가 없다. 클래스까지 간다는 것은 바이트 코드에서도 유지하겠다는 것
// 런타임때도 필요 없고, 오로지 소스 레벨에서 읽어서 애노테이션 프로세서가 처리 후 특정 소스 파일 만들고 컴파일 후, 
// 새로운 클래스인 MagicMoja를 만든다.
@Retention(RetentionPolicy.SOURCE)

// TYPE일 경우: 인터페이스, 클래스 ,이늄에 사용할 수 있다. 
@Target(ElementType.TYPE)
public @interface Magic {

}



// 자바가 제공하는 추상클래스인 AbstractProcessor를 구현
// 프로세서 인터페이스 구현 시 필요한 메서드를 기본적으로 구현해놨다.
public class MagicMojaProcessor extends AbstractProcessor {

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		// return Set.of(Magic.class.getName()); // jdk 9 버전 이후로 Set.of가 작동하는 듯...
		return null;
	}
	
	// 어떤 소스 코드 버전을 지원하는지 적어줄수있다.
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
	
	// 소스 코드의 구성 요소를 엘리먼트라 부르고, 각각의 엘리먼트들이 인자1로 참조할 수 있다.
	// RoundEnvironment는 애노테이션 프로세서는 라운드 개념으로 동작한다.
	// 여러 라운드에 걸쳐 처리를 하고, 각 라운드 마다 프로세서한테 특정 애노테이션을 찾으면 처리하고, 또 처리된 결과가 다음 라운드에 가서... 
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// 여기서 리턴하는 값이 true면, 이 애노테이션 타입을 처리 한거고, 다음 프로세서들한테 다음 프로세서한테 처리하라고 하지 않는다.
		
		// 매직 클래스의 애노테이션을 다 가지고 온다.
		Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
		
		for (Element element : elements) {
			Name elementName = element.getSimpleName();
			if (element.getKind() != ElementKind.INTERFACE) {
				// 인터페이스 엘리먼트가 아닌 경우 에러 처리
				processingEnv.getMessager().printMessage(Kind.ERROR, "Magic annotation can not be used on " + elementName);
			} else {
				processingEnv.getMessager().printMessage(Kind.NOTE, "Processing " + elementName);
			}
		}
		
		return false;
	}

}




// java/resources 파일을 만들 후 클래스 파일 경로로 지정해주고, META-INF폴더를 만들고, 
// services폴더를 만든 후 MagicMojaProcessor가 상속받고 있는 AbstractProceesor을 타고 들어가고
// AbstractProcessor가 impl하고 있는 Processor의 경로의 파일을 만들어준다.
// 파일 이름: javax.annotation.processing.Processor

# 이 파일의 사용 시점이 소스 컴파일 할 때가 아닌데, 소스 컴파일 시점 (메이븐 빌드 과정)에 이 파일이 동작하려 한다.
# 그러나 이 시점에는 MagicMojaProcessor가 없다.
#com.test.magic.moja.MagicMojaProcessor
# 따라서 mvn clean install을 먼저 한다. 
# 그러면 이 파일을 안쓰고 컴파일 후 jar packaging을 한다 그리고 주석을 풀고 
# 그냥 mvn install (참고로 clean을 하지 않으면 타겟 디렉토리를 비우지 않고 그대로 써서 패키징한다.)
# 그래서 컴파일된 거를 쓸 수 있다.

// 그러나 이 과정이 불편하기 때문에 등록을 도와주는 또 다른 애노테이션 프로세서가 있다.
// AutoService라는 프로젝트이다.
// 훨씬 간단하게 manifast파일을 컴파일할 때 자동으로 생성해준다.
// 사용 방법은
// 디펜던시에 의존성 추가
// 프로세스 위에 애노테이션 추가만 해주면 된다.
// 나의 경우 MagicMojaProcessor!

// 이 클래스를 컴파일할 때 javax.annotation.processing.Processor를 자동으로 만들어준다.
@AutoService(Processor.class)
public class MagicMojaProcessor extends AbstractProcessor { }

// 그리고 다른 프로젝트에서 의존성을 주입하여 사용!


```
    
- ServiceProvider
    - 확장 포인트를 제공하는... 알아보기...ㅎㅎ
    
- 소스코드 생성에 쓸 수 있는 유용한 Lib -> javapoet
    - 굉장히 직관적
    - dependency 추가!

```java
// *** 프로젝트2
// 자바가 제공하는 추상클래스인 AbstractProcessor를 구현
// 프로세서 인터페이스 구현 시 필요한 메서드를 기본적으로 구현해놨다.
public class MagicMojaProcessor extends AbstractProcessor {

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		// return Set.of(Magic.class.getName()); // jdk 9 버전 이후로 Set.of가 작동하는 듯...
		return null;
	}
	
	// 어떤 소스 코드 버전을 지원하는지 적어줄수있다.
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
	
	// 소스 코드의 구성 요소를 엘리먼트라 부르고, 각각의 엘리먼트들이 인자1로 참조할 수 있다.
	// RoundEnvironment는 애노테이션 프로세서는 라운드 개념으로 동작한다.
	// 여러 라운드에 걸쳐 처리를 하고, 각 라운드 마다 프로세서한테 특정 애노테이션을 찾으면 처리하고, 또 처리된 결과가 다음 라운드에 가서... 
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// 여기서 리턴하는 값이 true면, 이 애노테이션 타입을 처리 한거고, 다음 프로세서들한테 다음 프로세서한테 처리하라고 하지 않는다.
		
		// 매직 클래스의 애노테이션을 다 가지고 온다.
		Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
		
		for (Element element : elements) {
			Name elementName = element.getSimpleName();
			if (element.getKind() != ElementKind.INTERFACE) {
				// 인터페이스 엘리먼트가 아닌 경우 에러 처리
				processingEnv.getMessager().printMessage(Kind.ERROR, "Magic annotation can not be used on " + elementName);
			} else {
				processingEnv.getMessager().printMessage(Kind.NOTE, "Processing " + elementName);
			}
			
			TypeElement typeElement = (TypeElement) element;
			// typeElement를 가지고 있으면 클래스네임으로 변경할 수 있다. (javapoet)
			ClassName className = ClassName.get(typeElement);
			
			
			// 구현체 생성
			MethodSpec pullOut = MethodSpec.methodBuilder("pullOut")
					.addModifiers(Modifier.PUBLIC)
					.returns(String.class)
					.addStatement("return $s", "Rabbit!")
					.build();
			
			// 메서드를 클래스 안에 넣기
			// 1. MagicMoja 클래스 만들고 public class로!
			// 2. pullOut이라는 MethodSpec을 넣어주면  MagicMoja 안에 pullOut 메서드가 만들어진다.
			// 빌드를 하면 스펙이 만들어졌고, 스펙을 가지고 소스파일을 만들어 쓰면 된다.
			// 소스 코드를 객체로 만들었다고 보면 된다.
			// MagicMoja 안에 
			TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja")
					.addModifiers(Modifier.PUBLIC)
					.addSuperinterface(className)  // 매직이란 애노테이션이 붙어있는 클래스 네임을 구현하는 임플이 들어간다. 즉 public class MagicMoja implements Moja가 된다.  
					.addMethod(pullOut)
					.build();
			
			// 객체를 만들어 쓰기!
			// getFiler 인터페이스가 소스 코드, 클래스 코드, 및 리소스를 생성할 수 있는 핵심적인 인터페이스
			Filer filer = processingEnv.getFiler();
			
			// class에 여러 정보를 참조 가능
			
			
			// javapoet을 만들면 손쉽게 만들 수 있다.
			// java 파일을 만들고, 인자 1에 패키지, 인자 2에 매직 모자 타입을 만들겠다.
			try {
			JavaFile.builder(className.packageName(), magicMoja)
				.builder()
				.writeTo(filer);
			// 위는 애노테이션 프로세서가 제공하는 filer에 바로 쓸 수 있다. 그러면 소스 파일이 파일러에 만들어지고
			// java 컴파일러가 컴파일 한 클래스가 생성이 된다.
			} catch(IOException e) {
				// 컴파일 시 문제가 생기면 메시지 처리
				processingEnv.getMessager().printMessage(Kind.ERROR, "Fatal error: " + e);
			}
			// 여기까지 하고 mvn clean install 하여 로컬 인스톨되는 jar파일을 최신 코드로 바꿔주고~
			// mvn celan compile
			
			// 그러면 .class 파일이 생기게 된다.
			// 그러나 타 프로젝트에서 참조하여 사용하기 위해서는 소스 디렉토리로 인식시켜줘야 한다.
			// 그러기 위해서는 인텔리 기준으로  모듈 세팅을 열어서 generated-sources 밑에
			// annotation 디렉토리를 소스 추가해준다.
			
		}
		
		return true;
	}

}


```    
    


#### 애노테이션 프로세서 정리
- 소스코드 레벨에서 소스코드에 붙어있는 애노테이션 정보를 읽어 컴파일러가 컴파일 도 중에 새로운 소스 코드를 생성하거나 기존의 코드까지도 롬복처럼 바꿀순 있지만(비권장), 클래스(바이트 코드)를 생성할 수 있고 소스 코드와 별개의 리소스 파일을 생성할 수 있는 강력한 기능!
- 사용 예
    - 롬복
    - AutoService: java.util.ServiceLoador용 파일 생성 유틸리티
    - @Override
    - Dagger (컴파일 타임에 디펜던시 인젝션 제공! 런타임의 비용을 줄일 수 있다.)
    - 안드로이드 라이브러리
    
    
    