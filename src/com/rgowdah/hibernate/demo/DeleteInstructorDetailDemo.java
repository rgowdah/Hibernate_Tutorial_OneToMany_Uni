package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;

public class DeleteInstructorDetailDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).addAnnotatedClass(Instructor_Detail.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{
			//start transaction
			session.beginTransaction();
			//get the instructor detail object
			int id=3;
			Instructor_Detail instructor_Detail=session.get(Instructor_Detail.class, id);
			//print the instructor detail
			System.out.println("Instructor Detail: "+instructor_Detail);			
			//print the associated instructor
			System.out.println("Associated Instructor:"+instructor_Detail.getInstructor());
			//remove the associated object reference
			//break bidirectional link
			instructor_Detail.getInstructor().setInstructor_Detail(null);
			//now lets delete the instructor detail
			session.delete(instructor_Detail);
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
