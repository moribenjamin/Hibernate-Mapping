package com.moribenjamin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moribenjamin.hibernate.demo.entity.Course;
import com.moribenjamin.hibernate.demo.entity.Instructor;
import com.moribenjamin.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			
			//Creae the objects
			Instructor tempInstructor = new Instructor("Musan", "Public", "susan.public@luv2code.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
				
			//start a transaction
			session.beginTransaction();
			
			//save the insturctor
			//Note: this will also saver the datails object because of CascadeType.all
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
	
			
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
