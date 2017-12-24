package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;

public class DeleteDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).addAnnotatedClass(Instructor_Detail.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{
			//start transaction
			session.beginTransaction();
			//get instructor by primary key
			int id=1;
			Instructor instructor=session.get(Instructor.class, id);
			System.out.println("Found instructor: "+instructor);
			//delete the instructors
			if(instructor!=null){
				System.out.println("Deleting instructor with instructor details");
				session.delete(instructor);
			}
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
