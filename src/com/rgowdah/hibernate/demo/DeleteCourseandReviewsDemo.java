package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Course;
import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;
import com.rgowdah.hibernate.entity.Review;

public class DeleteCourseandReviewsDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).addAnnotatedClass(Instructor_Detail.class).
				addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{			
			//start transaction
			session.beginTransaction();
			int id=10;
			//get the course
			Course course=session.get(Course.class, id);
			//print the course
			System.out.println("Deleting the course");
			System.out.println(course);
			//print course reviews
			System.out.println(course.getReviews());
			//delete the course
			session.delete(course);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}catch (Exception exc) {
			exc.printStackTrace();
		}finally{
			session.close();
			sessionFactory.close();
		}
	}
}
