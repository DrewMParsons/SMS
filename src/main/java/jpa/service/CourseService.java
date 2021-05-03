package jpa.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO
{
	EntityManagerFactory emfactory = null;
	EntityManager em=null;

	
	/**
	 * This method takes no parameter and returns every Course in the table.
	 * 
	 * @return	list of courses in the table
	 * @see	Course
	 */
	@Override
	public List<Course> getAllCourses()
	{
		try
		{
			emfactory = Persistence.createEntityManagerFactory("SMS");
			em = emfactory.createEntityManager();
			Query findAllCourses = em.createNamedQuery("Course.findAll");
			@SuppressWarnings("unchecked")
			List <Course> courses = findAllCourses.getResultList();
			return courses;
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally {
			if(em !=null) {em.close();}
			if(emfactory!=null) {emfactory.close();}	
		}
		return null;
	
		
	}

}
