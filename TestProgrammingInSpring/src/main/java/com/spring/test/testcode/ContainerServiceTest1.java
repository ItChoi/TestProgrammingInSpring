package com.spring.test.testcode;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ContainerServiceTest1 {
	
	public static <T> T getObject(Class<T> classType) {
		T instance = createInstance(classType);
		
		Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
			if (f.getAnnotation(SangHyun.class) != null) {
				Object fieldInstance = createInstance(f.getType());
				f.setAccessible(true);
				try {
					f.set(instance, fieldInstance);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		return instance;
	}
	
	private static <T> T createInstance(Class<T> classType) {
		try {
			return classType.getConstructor(null).newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException();
		}
	}

}
