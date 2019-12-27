package com.spring.test.junit5;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// 이 애노테이션을 사용할 때 이 애노테이션 정보를 런타임까지 유지..
@Retention(RetentionPolicy.RUNTIME)

// 두 개의 메타 애노테이션을 사용
@Test
@Tag("fast")
// FastTest라는 Composed(여러 개의 다른 애노테이션을 조합해서 만든) 새로운 애노테이션을 만듬!
public @interface FastTest {

}
