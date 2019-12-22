package com.spring.test.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

	@Autowired
	MainRepository mainRepository;
	
}
