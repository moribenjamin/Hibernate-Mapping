package com.moribenjamin.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.moribenjamin.hibernate.demo.entity.Course;
import com.moribenjamin.hibernate.demo.entity.Instructor;
import com.moribenjamin.hibernate.demo.entity.InstructorDetail;
import com.moribenjamin.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
	
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();	
		
		//use the session object to save Java object
		try {
				
			//start a transaction
			session.beginTransaction();
		
			//create a course
			Course tempCourse = new Course("Pac-Man: How to score one million Points");
			
			//addsome reviews
			tempCourse.addReview(new Review("Great course...loved it!"));
			tempCourse.addReview(new Review("Cool course, job well done"));
			tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));
			
			//save the course...and leverage the cascade all :-)
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
			
			
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
