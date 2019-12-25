package com.spring.test.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 런타임 참조 할 애노테이션은 리텐션을 반드시 설정.
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

}
