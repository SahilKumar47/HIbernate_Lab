package com.manytomany.controller;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.manytomany.entities.Animal;
import com.manytomany.entities.FoodItem;
import com.manytomany.factory.HibernateSessionFactory;

public class Tester {
	public static void main(String[] args) {
		SessionFactory factory = HibernateSessionFactory.getSessionFactory();
		
		Animal parrot = new Animal("birds", 30);
		Animal tiger = new Animal("Carnivore", 18);
		
		FoodItem beans = new FoodItem("beans", "50");
		FoodItem chicken = new FoodItem("meat", "100");
		FoodItem potatoes = new FoodItem("Vegitable", "42");
		
		parrot.getFoodItemList().add(beans);
		parrot.getFoodItemList().add(potatoes);
		tiger.getFoodItemList().add(chicken);
		tiger.getFoodItemList().add(potatoes);
		
		beans.getAnimalList().add(parrot);
		chicken.getAnimalList().add(tiger);
		potatoes.getAnimalList().add(parrot);
		potatoes.getAnimalList().add(tiger);
		
		Session session = factory.openSession();
		Transaction transaction = session.getTransaction();

		try {
			transaction.begin();

			session.persist(potatoes);
			session.persist(beans);
			session.persist(chicken);
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		}
		session.close();
		factory.close();
	}
}
