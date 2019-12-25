package com.spring.test.di;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerService {

	
	// 메서드 제네릭<T>: 들어오는 클래스 타입으로 리턴 ! (오브젝트 리턴이 아닌..)
	public static <T> T getObject(Class<T> classType) {
		T instance = createInstance(classType);
		Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
			if (f.getAnnotation(Inject.class) != null) {
				Object fieldInstance = createInstance(f.getType());
				f.setAccessible(true);
				try {
					f.set(instance, fieldInstance);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException();
				}
			}
		});
		
		return instance;
	}
	
	private static <T> T createInstance(Class<T> classType) {
		try {
			return classType.getConstructor(null).newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		} 
	}
	
	
}
