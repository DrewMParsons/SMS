package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public interface StudentDAO
{
	List<Student> getAllStudents();
	Student getStudentByEmail(String studentEmail);
	boolean validateStudent(String studentEmail, String password);
	boolean registerStudentToCourse(String studentEmail,int courseId);
	List<Course> getStudentCourses(String studentEmail);


}
