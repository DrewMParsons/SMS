package jpa.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO
{
	EntityManagerFactory emfactory = null;
	EntityManager em = null;

	/**
	 * This method reads the student table in your database and returns the data as
	 * a List<Student>
	 * 
	 * @return List<Student>
	 * @see Student
	 */
	
	@Override
	public List<Student> getAllStudents()
	{
		try
		{
			emfactory = Persistence.createEntityManagerFactory("SMS");
			em = emfactory.createEntityManager();
			Query findAllStudents = em.createNamedQuery("Student.findAll");
			@SuppressWarnings("unchecked")
			List<Student> students = findAllStudents.getResultList();
			em.close();
			emfactory.close();
			return students;
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(em !=null) {em.close();}
			if(emfactory!=null) {emfactory.close();}
			
		}
		return null;

	}

	/**
	 * This method takes a Student’s email as a String and parses the student list
	 * for a Student with that email and returns a Student Object
	 * 
	 * @param String student email
	 * @return Student
	 * @see Student
	 */
	@Override
	public Student getStudentByEmail(String studentEmail) throws IllegalArgumentException 
	{
		if(studentEmail == null || studentEmail.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("Email: %s is not valid",studentEmail));}
		
		try
		{
			emfactory = Persistence.createEntityManagerFactory("SMS");
			em = emfactory.createEntityManager();

			Student foundStudent = em.find(Student.class, studentEmail);
			
			return foundStudent;
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(em !=null) {em.close();}
			if(emfactory!=null) {emfactory.close();}
			
		}
		return null;
		
		

	}

	/**
	 * This method takes two parameters: the first one is the user email and the
	 * second one is the password from the user input. Return whether or not student
	 * was found.
	 * 
	 * @param String student email
	 * @param String student password
	 * @return boolean true if student exists in database
	 * @see Student
	 */
	@Override
	public boolean validateStudent(String studentEmail, String password)
	{
		try {
		Student isValidStudent = getStudentByEmail(studentEmail);
		if (isValidStudent != null)
		{
			if (isValidStudent.getsPass().equals(password))
			{
				return true;
			}
		}
		return false;
		}catch(IllegalArgumentException iae) {
			System.out.println("Neither Email nor Password may be empty");
			return false;
		}

	}

	@Override
	public boolean registerStudentToCourse(String studentEmail, int courseId)
	{
		try
		{
			Course registerCourse;
			CourseService cs = new CourseService();
			Student stu = getStudentByEmail(studentEmail);
			List<Course> registeredCourses = getStudentCourses(studentEmail);
			for (Course c : registeredCourses)
			{
				if (c.getCid() == courseId)
				{
					System.out.println("Already registered to that class");
					return false;
					
				}
				
			}
			List<Course> courses = cs.getAllCourses();
			try {
			registerCourse = courses.stream().filter(c -> c.getCid() == courseId).findFirst().get();
			}catch(NoSuchElementException e) {
				System.out.println("Class number: "+courseId+" is not a valid class");
				return false;
			}
			
			emfactory = Persistence.createEntityManagerFactory("SMS");
			em = emfactory.createEntityManager();
			em.getTransaction().begin();
			
			stu.getsCourses().add(registerCourse);
			em.merge(stu);
			em.getTransaction().commit();
			System.out.println("Class Registered!");
			return true;
		} catch (IllegalArgumentException e)
		{
			System.out.println(String.format("Email: %s is not valid",studentEmail));
			return false;
		}finally {
			if(em !=null && em.isOpen()) {em.close();}
			if(emfactory!=null && emfactory.isOpen()) {emfactory.close();}
		}
	}

	@Override
	public List<Course> getStudentCourses(String studentEmail)
	{
		try {
		Student stu = getStudentByEmail(studentEmail);
		return stu.getsCourses();
		}catch(IllegalArgumentException iae) {
			System.out.println(String.format("Email: %s is not valid",studentEmail));
			return null;
		}
//		
	}

}
