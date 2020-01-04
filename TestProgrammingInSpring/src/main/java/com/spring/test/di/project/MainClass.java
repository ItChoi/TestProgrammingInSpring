package com.spring.test.di.project;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class MainClass {
	public static void main(String[] args) {
		DependencyInjection di = new DependencyInjection();
		
		String[] ids = {
				"11", "22", "33", 
				"44", "55", "66",
				"77", "88", "99"
		};
		
		String[] names = {
				"이름11", "이름22", "이름33", 
				"이름44", "이름55", "이름66",
				"이름77", "이름88", "이름99"
		};
		
		String[] phoneNumbers = {
				"폰11", "폰22", "폰33", 
				"폰44", "폰55", "폰66",
				"폰77", "폰88", "폰99"
		};
		
		for (int i = 0; i < 9; i++) {
			di.studentService.insert(new Student(ids[i], names[i], phoneNumbers[i]));
		}
		
		di.studentService.insert(new Student("12345", "12345", "12345"));
		
		
		di.studentService.update("12345", new Student("123456666", "123456666", "123456666"));
		System.out.println(di.studentService.selectAll());
		
	}
}

class DependencyInjection {
	StudentService studentService;

	public DependencyInjection() {
		StudentDao studentDao = new StudentDao();
		studentService = new StudentServiceImpl(studentDao);
	}
	
}

interface StudentService {
	void insert(Student student);
	void update(String id, Student student);
	void delete(String id);
	Student select(String id);
	String selectAll();
}

class StudentServiceImpl implements StudentService {
	
	StudentDao studentDao;

	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public void insert(Student student) {
		studentDao.insert(student);
	}

	@Override
	public void update(String id, Student student) {
		if (checkDuplicationId(id)) {
			System.out.println("아이디가 존재하지 않습니다.");
		} else {
			studentDao.update(student.getId(), student);
		}
	}

	@Override
	public void delete(String id) {
		studentDao.delete(id);
	}

	@Override
	public Student select(String id) {
		if (checkDuplicationId(id)) {
			System.out.println("아이디가 존재하지 않습니다.");
			return null;
		} else {
			return studentDao.select(id);
		}
	}

	@Override
	public String selectAll() {
		return studentDao.selectAll();
	}
	
	private boolean checkDuplicationId(String id) {
		if (studentDao.select(id) == null) {
			return true;
		}
		
		return false;
	}
	
}

class StudentDao {
	
	Map<String, Student> map = new HashMap<>();
	
	public void insert(Student student) {
		map.put(student.getId(), student);
	}

	public void update(String id, Student student) {
		map.put(id, student);
	}

	public void delete(String id) {
		map.remove(id);
	}

	public Student select(String id) {
		return map.get(id);
	}

	public String selectAll() {
		return map.toString();
	}
	
}

@Getter
@Setter
class Student {
	private String id;
	private String name;
	private String phoneNumer;
	
	public Student(String id, String name, String phoneNumer) {
		this.id = id;
		this.name = name;
		this.phoneNumer = phoneNumer;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", phoneNumer=" + phoneNumer + "]\n";
	}
	
	
	
}