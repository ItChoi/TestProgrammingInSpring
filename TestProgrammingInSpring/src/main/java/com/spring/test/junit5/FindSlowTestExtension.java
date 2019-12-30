package com.spring.test.junit5;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

	private long THRESHOLD;
	
	public FindSlowTestExtension(long tHRESHOLD) {
		super();
		THRESHOLD = tHRESHOLD;
	}

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		// ExtensionContext - store 인터페이스가 있는데, 데이터를 넣고 뺴는 용도
		ExtensionContext.Store store = getStore(context);
		store.put("START_TIME", System.currentTimeMillis());
	}
	
	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		Method requiredTestMethod = context.getRequiredTestMethod();
		
		SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
		
		String testMethodName = context.getRequiredTestMethod().getName();
		
		// ExtensionContext - store 인터페이스가 있는데, 데이터를 넣고 뺴는 용도
		ExtensionContext.Store store = getStore(context);
		// START_TIME을 지움과 동시에 값을 long 타입으로 받으면 현재 시간을 가지고 올 수 있다.
		long startTime = store.remove("START_TIME", long.class);
		// 현재시간에서 뺴면 얼마나 걸렸는지 구한다.
		long duration = System.currentTimeMillis() - startTime;
		
		// 작업 시간이 1초를 넘기면 @SlowTest를 붙이라고 권장.
		if (duration > THRESHOLD && annotation == null) {
			System.out.printf("Please consider mark method [%s] with @SlowTest.\n", testMethodName);
		}
	}
	
	private ExtensionContext.Store getStore(ExtensionContext context) {
		// 테스트 클래스 이름 + 메소드 이름 조합한 네임스페이스
		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		
		return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
	}
}
