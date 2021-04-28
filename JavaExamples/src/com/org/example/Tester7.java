package com.org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class Student {
	private int studentId;
	private String studentName;
	private boolean courseRegistered;

	public Student(int studentId, String studentName, boolean courseRegistered) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.courseRegistered = courseRegistered;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public boolean isCourseRegistered() {
		return courseRegistered;
	}

	public void setCourseRegistered(boolean courseRegistered) {
		this.courseRegistered = courseRegistered;
	}

}



public class Tester7 {

	public static void main(String[] args) {
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1001, "Steve", true));
		students.add(new Student(1002, "Rachel", false));
		students.add(new Student(1003, "Monica", true));
		students.add(new Student(1004, "David", true));

		ListIterator<Student> item = students.listIterator();
		System.out.println("Student names");
		while (item.hasNext()) {
			System.out.println(item.next().getStudentName());
		}
        
        System.out.println();
        
		System.out.println("Student names in reverse order");
		while (item.hasPrevious()) {
			System.out.println(item.previous().getStudentName());
		}

	}


}
