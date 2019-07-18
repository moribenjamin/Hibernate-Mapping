package com.moribenjamin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moribenjamin.hibernate.demo.entity.Course;
import com.moribenjamin.hibernate.demo.entity.Instructor;
import com.moribenjamin.hibernate.demo.entity.InstructorDetail;
import com.moribenjamin.hibernate.demo.entity.Review;
import com.moribenjamin.hibernate.demo.entity.Student;

public class DeletePacmanCourseDemo {

	public static void main(String[] args) {
	
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();	
		
		//use the session object to save Java object
		try {
				
			//start a transaction
			session.beginTransaction();
		
			//get the pacman course from db
			int courseId = 10;
			Course tempCourse = session.get(Course.class, courseId);
			
	
			//delete the ccourse
			System.out.println("Deleting course: " + tempCourse);
			
			session.delete(tempCourse);
			
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
