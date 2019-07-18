package com.moribenjamin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moribenjamin.hibernate.demo.entity.Course;
import com.moribenjamin.hibernate.demo.entity.Instructor;
import com.moribenjamin.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
	
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();	
		
		//use the session object to save Java object
		try {
				
			//start a transaction
			session.beginTransaction();
		
			//get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			//Create some Courses
			Course tempCourse1 = new Course("Break Dancing 101");
			Course tempCourse2 = new Course("The Ultimate Camping Course");
			
			
			//saver the courses
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			//saver the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			//add clean up code
			session.close();
			
			factory.close();
		}
	}

}
