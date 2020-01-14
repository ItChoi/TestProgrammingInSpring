package com.spring.test.lambda;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class LambdaTest {

	@Test
	void 람다_사용_테스트01() {
		TestInterface ti = (aa, bb) -> {
			System.out.println("test: " + (aa + bb));
		};
		
		ti.sumValue(1, 23);
	}
	

}

@FunctionalInterface
interface TestInterface {
	public void sumValue(int a, int b);
}
