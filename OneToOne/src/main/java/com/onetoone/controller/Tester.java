package com.onetoone.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.onetoone.entities.Animal;
import com.onetoone.entities.Cage;
import com.onetoone.factory.HibernateSessionFactory;

public class Tester {
	public static void main(String[] args) {
		Animal animal1 = new Animal("lion", 8);
		Animal animal2 = new Animal("Tiger", 3);
		Animal animal3 = new Animal("elephant", 9);
		Animal animal4 = new Animal("deer", 4);
		Animal animal5 = new Animal("snake", 10);

		Cage cage1 = new Cage("A4", "central");
		Cage cage2 = new Cage("B3", "entrace");
		Cage cage3 = new Cage("D7", "back");
		Cage cage4 = new Cage("A3", "upward");
		Cage cage5 = new Cage("C2", "left");

		cage1.setAnimal(animal1);
		cage2.setAnimal(animal2);
		cage3.setAnimal(animal3);
		cage4.setAnimal(animal4);
		cage5.setAnimal(animal5);

		animal1.setCage(cage1);
		animal2.setCage(cage2);
		animal3.setCage(cage3);
		animal4.setCage(cage4);
		animal5.setCage(cage5);

		SessionFactory factory = HibernateSessionFactory.getSessionFactory();

		Session session = factory.openSession();

		Transaction tx = session.getTransaction();
		tx.begin();
		// To persist the data into the database
//		session.persist(animal1);
//		session.persist(animal2);
//		session.persist(animal3);
//		session.persist(animal4);
//		session.persist(animal5);
//		
//		session.persist(cage1);
//		session.persist(cage2);
//		session.persist(cage3);
//		session.persist(cage4);
//		session.persist(cage5);
		List<Cage> cages = session.createQuery("select c from Cage c").getResultList();
		for(Cage c: cages) {
			System.out.println("-------------Cages-----------");
			System.out.println(c);
		}
		tx.commit();
		session.close();
		factory.close();

	}
}
