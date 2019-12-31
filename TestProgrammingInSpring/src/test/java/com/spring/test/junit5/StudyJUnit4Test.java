package com.spring.test.junit5;

import org.junit.Before;
import org.junit.Test;

public class StudyJUnit4Test {

	// JUnit4로 돌려도 vintage 의존성을 추가하였기 때문에
	// JUnit5에 있는 4로 돌리고 있다.
	
	@Before
	public void before() {
		System.out.println("before");
	}
	
	@Test
	public void createTest1() {
		System.out.println("test junit4");
	}
	
	@Test
	public void createTest2() {
		System.out.println("test junit4");
	}
	
}
