package com.spring.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;

import com.spring.test.di.BookRepository;
import com.spring.test.di.BookService;
import com.spring.test.di.ContainerService;

import com.spring.test.testcode.*;

public class ContainerServiceTest {
	
	@Ignore
	@Test
	public void getObject_BookRepository() {
		BookRepository bookRepository = ContainerService.getObject(BookRepository.class);
		assertNotNull(bookRepository);
	}
	
	@Ignore
	@Test
	public void getObject_BookService() {
		BookService bookService = ContainerService.getObject(BookService.class);
		assertNotNull(bookService);
		assertNotNull(bookService.bookRepository);
	}
	
	@Test
	public void getObjectRepository() {
		BookRepositoryTest bookRepository = ContainerServiceTest1.getObject(BookRepositoryTest.class);
		
		assertNotNull(bookRepository);
	}
	
	@Test
	public void getObjectService() {
		
	}
	
	
}















