package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;

public class CreateDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).addAnnotatedClass(Instructor_Detail.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{
			//create the objects
			Instructor instructor=new Instructor("abc","efg","abcefg.me");
			Instructor_Detail instructor_Detail=new Instructor_Detail("abcefg.me","love to code");
			//associate the objects
			instructor.setInstructor_Detail(instructor_Detail);
			//start transaction
			session.beginTransaction();
			//save instructor object
			//this will save all associated objects
			System.out.println("Saving Instructor: "+instructor);
			session.save(instructor);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}catch (Exception exc) {
			exc.printStackTrace();
		}finally{
			sessionFactory.close();
		}
	}
}
