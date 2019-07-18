package com.moribenjamin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moribenjamin.hibernate.demo.entity.Instructor;
import com.moribenjamin.hibernate.demo.entity.InstructorDetail;
import com.moribenjamin.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
	
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();	
		
		//use the session object to save Java object
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor detail object
			int theId = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associated instructor: " + tempInstructorDetail.getInsturctor());
			
			//delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			
			//remove the associated object reference that was bid-directionally linked
			tempInstructorDetail.getInsturctor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception exc) {
			
			exc.printStackTrace();
			
		} finally {
		
			//handle connection leak issue
			session.close();
			
			factory.close();
		}
	}

}
